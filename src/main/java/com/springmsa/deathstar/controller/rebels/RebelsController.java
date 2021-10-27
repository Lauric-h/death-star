package com.springmsa.deathstar.controller.rebels;

import com.springmsa.deathstar.dao.BookingDao;
import com.springmsa.deathstar.model.Booking;
import com.springmsa.deathstar.model.Rebel;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("bookings/confirmation")
    public Rebel addBooking(@RequestBody Map<String, Object> rebelInfo){


        Rebel rebel = new Rebel();
        String firstName = (String) rebelInfo.get("firstName");
        rebel.setFirstName(firstName);
        String lastName = (String) rebelInfo.get("lastName");
        rebel.setLastName(lastName);
        LocalDate birthDate = LocalDate.parse((CharSequence) rebelInfo.get("birthDate"));
        rebel.setBirthDate(birthDate);
        String licenseNumber = (String) rebelInfo.get("licenseNumber");
        rebel.setLicenseNumber(licenseNumber);
        LocalDate licenseDate = LocalDate.parse((CharSequence) rebelInfo.get("licenseDate")) ;
        rebel.setLicenseDate(licenseDate);

        Rebel rebel1 = new RestTemplate().postForObject("http://localhost:8080/api/rebels", rebel, Rebel.class);
        System.out.println(rebel1);
        Booking booking = new Booking();
        int vehicleId = (int) rebelInfo.get("vehicleId");
        booking.setVehicleId(vehicleId);
        LocalDate startDate = LocalDate.parse((CharSequence) rebelInfo.get("startDate"));
        booking.setStartDate(startDate);
        LocalDate endDate = LocalDate.parse((CharSequence) rebelInfo.get("endDate"));
        booking.setEndDate(endDate);
        assert rebel1 != null;
        booking.setClientId(rebel1.getId());
        bookingDao.save(booking);

        return null;
    }

}
