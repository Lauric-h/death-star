package com.springmsa.deathstar.controller.vehicles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springmsa.deathstar.dao.BookingDao;
import com.springmsa.deathstar.handlers.AvailableVehiclesHandler;
import com.springmsa.deathstar.handlers.ConditionsHandler;
import com.springmsa.deathstar.httprequest.RequestBuilder;
import com.springmsa.deathstar.model.Booking;
import com.springmsa.deathstar.model.Vehicle;
import com.springmsa.deathstar.model.VehicleBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        LocalDate queryStartDate = LocalDate.parse((CharSequence) rq.get("startDate"));
        LocalDate queryEndDate = LocalDate.parse((CharSequence) rq.get("endDate"));

        // Query all bookings between two dates
        List<Booking> bookingList = bookingDao.findAllByEndDateIsAfterAndStartDateIsBefore(queryStartDate, queryEndDate);

        // build list of available vehicles
        availableVehiclesHandler.buildVehicleRequestWithType(rq.get("vehicle_type"));
        return availableVehiclesHandler.getAvailableVehiclesArray(bookingList);
    }

    // post /api/bookings/book -> calls vehicles
    @PostMapping(value = "/api/bookings/book")
    public Object fetchAvailableVehicleById(@RequestBody Map<String, Object> rq) throws JsonProcessingException {
        String id = (String) rq.get("vehicleId");
        int estimatedKm = (int) rq.get("estimatedKm");
        LocalDate startDate = LocalDate.parse((CharSequence) rq.get("startDate"));
        LocalDate endDate = LocalDate.parse((CharSequence) rq.get("endDate"));
        LocalDate birthDate = LocalDate.parse((CharSequence) rq.get("birthDate"));
        String licenseNumber = (String) rq.get("licenseNumber");
        LocalDate licenseDate = LocalDate.parse((CharSequence) rq.get("licenseDate"));;

        // get vehicle info
        String url = RequestBuilder.buildUri(VEHICLE_SERVER, "/" + id);
        Vehicle vehicle = restTemplate.getForObject(url, Vehicle.class);
        assert vehicle != null;

        VehicleBooking vehicleBooking = new VehicleBooking(
            vehicle, estimatedKm,startDate, endDate, birthDate, licenseNumber, licenseDate
        );

        if (!vehicleBooking.canRentThisVehicle()) {
            return "Cannot rent this vehicle";
        }

        return vehicleBooking;
    }

}
