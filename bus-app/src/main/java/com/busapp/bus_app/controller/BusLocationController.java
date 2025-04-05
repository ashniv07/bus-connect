package com.busapp.bus_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.busapp.bus_app.DTO.BusLocationDto;
import com.busapp.bus_app.service.BusLocationService;
@CrossOrigin(origins = "*")

@RestController

public class BusLocationController {

    @Autowired
    private BusLocationService locationService;

    @PostMapping("/bus-location")
    public ResponseEntity<String> updateBusLocation(@RequestBody BusLocationDto dto) {
        locationService.updateBusLocation(dto);
        return ResponseEntity.ok("Location updated");
    }
}

