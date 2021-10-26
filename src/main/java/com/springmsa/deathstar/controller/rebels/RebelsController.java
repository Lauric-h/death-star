package com.springmsa.deathstar.controller.rebels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("api/bookings")
public class RebelsController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/confirmation")
    public String addRebel(){
        // recupère le post du front et envoyer le post du front dans api rebel


    return null;
    }


}
