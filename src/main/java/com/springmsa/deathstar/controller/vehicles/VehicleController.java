package com.springmsa.deathstar.controller.vehicles;

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

import java.util.List;
import java.util.Map;

@RestController
public class VehicleController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${VEHICLE_SERVER}")
    private String VEHICLE_SERVER;

    // post /api/bookings/vehicles -> calls vehicles
    @PostMapping(value = "/api/bookings/vehicles")
    public ResponseEntity<Vehicle[]> fetchAvailableVehicles(@RequestBody Map<String, Object> rq) {
        // Call to api vehicle to get all vehicles
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host(VEHICLE_SERVER).path("/type").build();

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(uriComponents.toUriString())
                .queryParam("type", rq.get("type"))
                .encode()
                .toUriString();

        // Get start and end dates to fetch vehicles currently booked


        // Exclude vehicules not available

        // return list

        return restTemplate.getForEntity(urlTemplate, Vehicle[].class);
    }

    // post /api/bookings/book -> calls vehicles
    @PostMapping(value = "/api/bookings/book")
    public void fetchAvailableVehicleById(@RequestBody Map<String, Object> rq) {
        System.out.println(rq);
    }

    // post /api/bookings/confirmation -> calls clients
}
