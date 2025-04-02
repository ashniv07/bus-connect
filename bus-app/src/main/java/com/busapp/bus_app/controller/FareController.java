package com.busapp.bus_app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.busapp.bus_app.DTO.FareResponse;
import com.busapp.bus_app.service.FareCalculationService;

@RestController
public class FareController {
    @Autowired
    private FareCalculationService fareCalculationService;

    @GetMapping("/calculate")
    public FareResponse getFare(@RequestParam String source, @RequestParam String destination) {
        return fareCalculationService.calculateFare(source, destination);
    }
}
