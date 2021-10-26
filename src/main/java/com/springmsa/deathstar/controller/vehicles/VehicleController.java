package com.springmsa.deathstar.controller.vehicles;

import com.springmsa.deathstar.dao.BookingDao;
import com.springmsa.deathstar.handlers.AvailableVehiclesHandler;
import com.springmsa.deathstar.httprequest.RequestBuilder;
import com.springmsa.deathstar.model.Booking;
import com.springmsa.deathstar.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

@RestController
public class VehicleController {
    @Autowired
    BookingDao bookingDao;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AvailableVehiclesHandler availableVehiclesHandler;


    @Value("${VEHICLE_SERVER}")
    private String VEHICLE_SERVER;

    // post /api/bookings/vehicles -> calls vehicles
    @PostMapping(value = "/api/bookings/vehicles")
    public ArrayList<Vehicle> fetchAvailableVehicles(@RequestBody Map<String, Object> rq) {
        // Get start and end dates to fetch vehicles currently booked
        LocalDate queryStartDate = LocalDate.parse((CharSequence) rq.get("start"));
        LocalDate queryEndDate = LocalDate.parse((CharSequence) rq.get("end"));

        // Query all bookings between two dates
        List<Booking> bookingList = bookingDao.findAllByEndDateIsAfterAndStartDateIsBefore(queryStartDate, queryEndDate);

        // build list of available vehicles
        availableVehiclesHandler.buildVehicleRequestWithType(rq.get("type"));
        return availableVehiclesHandler.getAvailableVehiclesArray(bookingList);
    }

    // post /api/bookings/book -> calls vehicles
    @PostMapping(value = "/api/bookings/book")
    public void fetchAvailableVehicleById(@RequestBody Map<String, Object> rq) {
        System.out.println(rq);
    }

}
