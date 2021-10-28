package com.springmsa.deathstar.controller.rebels;

import com.springmsa.deathstar.dao.BookingDao;
import com.springmsa.deathstar.httprequest.RequestBuilder;
import com.springmsa.deathstar.model.Booking;
import com.springmsa.deathstar.model.Rebel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("api")
public class RebelsController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    BookingDao bookingDao;

    @Value(value = "${REBELS_SERVER}")
    private String REBELS_SERVER;

    @PostMapping("bookings/confirmation")
    public Booking addBooking(@RequestBody Map<String, Object> rebelInfo){
        // Creates new rebel
        Rebel queryRebel = new Rebel(
            (String) rebelInfo.get("lastName"),
            (String) rebelInfo.get("firstName"),
            LocalDate.parse((CharSequence) rebelInfo.get("birthDate")),
            (String) rebelInfo.get("licenseNumber"),
            LocalDate.parse((CharSequence) rebelInfo.get("licenseDate"))
        );

        // Post new rebel to Rebels API
        String request = RequestBuilder.buildUri(REBELS_SERVER);
        Rebel postRebel = new RestTemplate().postForObject(request, queryRebel, Rebel.class);
        assert postRebel != null;

        // Creates new Booking
        Booking booking = new Booking(
                (int) rebelInfo.get("vehicleId"),
                postRebel.getId(),
                LocalDate.parse((CharSequence) rebelInfo.get("startDate")),
                LocalDate.parse((CharSequence) rebelInfo.get("endDate"))
        );
        bookingDao.save(booking);

        return booking;
    }

}
