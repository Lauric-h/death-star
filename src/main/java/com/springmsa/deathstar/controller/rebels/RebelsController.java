package com.springmsa.deathstar.controller.rebels;

import com.springmsa.deathstar.model.Rebel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("api")
public class RebelsController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("bookings/confirmation")
    public Rebel addBooking(@RequestBody Map<String, Object> rebelInfo){

        Rebel rebel = new Rebel();
        String firstName = (String) rebelInfo.get("firstName");
        rebel.setFirstName(firstName);
        String lastName = (String) rebelInfo.get("lastName");
        rebel.setLastName(lastName);
        Date birthDate = (Date) rebelInfo.get("birthDate");
        rebel.setBirthDate(birthDate);
        String licenseNumber = (String) rebelInfo.get("licenseNumber");
        rebel.setLicenseNumber(licenseNumber);
        Date licenseDate = (Date) rebelInfo.get("licenseDate");
        rebel.setLicenseDate(licenseDate);

        new RestTemplate().postForObject("http://localhost:8082/api/rebels", rebel, Rebel.class);

        return null;
    }
}
