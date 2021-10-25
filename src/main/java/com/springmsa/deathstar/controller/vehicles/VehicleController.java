package com.springmsa.deathstar.controller.vehicles;

import com.springmsa.deathstar.dao.BookingDao;
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

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

@RestController
public class VehicleController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    BookingDao bookingDao;

    @Value("${VEHICLE_SERVER}")
    private String VEHICLE_SERVER;

    // post /api/bookings/vehicles -> calls vehicles
    @PostMapping(value = "/api/bookings/vehicles")
    public ArrayList<Vehicle> fetchAvailableVehicles(@RequestBody Map<String, Object> rq) {
        // Call to api vehicle to get all vehicles
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host(VEHICLE_SERVER).path("/type").build();

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(uriComponents.toUriString())
                .queryParam("type", rq.get("type"))
                .encode()
                .toUriString();

        // Get start and end dates to fetch vehicles currently booked
        LocalDate queryStartDate = LocalDate.parse((CharSequence) rq.get("start"));
        LocalDate queryEndDate = LocalDate.parse((CharSequence) rq.get("end"));

        // Query all bookings between two dates
        List<Booking> bookingList = bookingDao.findAllByEndDateIsAfterAndStartDateIsBefore(queryStartDate, queryEndDate);

        System.out.println(bookingList);

        ResponseEntity<Vehicle[]> response = restTemplate.getForEntity(urlTemplate, Vehicle[].class);
        Vehicle[] vehicleList = response.getBody();
        assert vehicleList != null;

        ArrayList<Vehicle> vehicleArray = new ArrayList<Vehicle>(Arrays.asList(vehicleList));

        for (int i = 0; i < vehicleArray.size(); i++) {
            for (Booking booking : bookingList) {
                if (vehicleArray.get(i).getId() == booking.getVehicleId()) {
                    vehicleArray.remove(vehicleArray.get(i));
                }
            }
        }

        return vehicleArray;
    }

    // post /api/bookings/book -> calls vehicles
    @PostMapping(value = "/api/bookings/book")
    public void fetchAvailableVehicleById(@RequestBody Map<String, Object> rq) {
        System.out.println(rq);
    }

    // post /api/bookings/confirmation -> calls clients
}
