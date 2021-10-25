package com.springmsa.deathstar.controller.vehicles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class VehicleController {
    @Autowired
    private RestTemplate restTemplate;

    // post /api/bookings/vehicles -> calls vehicles
    @PostMapping(value = "/api/bookings/vehicles")
    public void fetchAvailableVehicles(@RequestBody Map<String, Object> rq) {
        System.out.println(rq.get("start"));
    }

    // post /api/bookings/book -> calls vehicles

    // post /api/bookings/confirmation -> calls clients
}
