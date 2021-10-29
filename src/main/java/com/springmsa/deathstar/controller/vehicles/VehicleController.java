package com.springmsa.deathstar.controller.vehicles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springmsa.deathstar.dao.BookingDao;
import com.springmsa.deathstar.handlers.AvailableVehiclesHandler;
import com.springmsa.deathstar.httprequest.RequestBuilder;
import com.springmsa.deathstar.model.Booking;
import com.springmsa.deathstar.model.Vehicle;
import com.springmsa.deathstar.model.VehicleBooking;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3333")
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

    @Operation(summary = "Get available vehicles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all the available vehicles",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
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

    @Operation(summary = "Get the wanted vehicle and returns price and validates conditions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the vehicle",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @PostMapping(value = "/api/bookings/book")
    public Object fetchAvailableVehicleById(@RequestBody Map<String, Object> rq) throws JsonProcessingException {
        int id = (int) rq.get("vehicleId");
        // get vehicle info
        String url = RequestBuilder.buildUriWithPath(VEHICLE_SERVER, "/" + id);
        Vehicle vehicle = restTemplate.getForObject(url, Vehicle.class);
        assert vehicle != null;

        VehicleBooking vehicleBooking = new VehicleBooking(
            vehicle,
            (int) rq.get("estimatedKm"),
            LocalDate.parse((CharSequence) rq.get("startDate")),
            LocalDate.parse((CharSequence) rq.get("endDate")),
            LocalDate.parse((CharSequence) rq.get("birthDate")),
            (String) rq.get("licenseNumber"),
            LocalDate.parse((CharSequence) rq.get("licenseDate"))
        );

        if (!vehicleBooking.canRentThisVehicle()) {
            return "Cannot rent this vehicle";
        }

        return vehicleBooking;
    }

}
