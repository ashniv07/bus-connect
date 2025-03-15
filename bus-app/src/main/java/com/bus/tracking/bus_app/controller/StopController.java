package com.bus.tracking.bus_app.controller;

import com.bus.tracking.bus_app.model.Stop;
import com.bus.tracking.bus_app.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stops")
public class StopController {

    @Autowired
    private StopService stopService;

    @PostMapping
    public ResponseEntity<Stop> createStop(@RequestBody Stop stop) {
        return ResponseEntity.ok(stopService.createStop(stop));
    }

    @GetMapping
    public ResponseEntity<List<Stop>> getAllStops() {
        return ResponseEntity.ok(stopService.getAllStops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stop> getStopById(@PathVariable String id) {
        return ResponseEntity.ok(stopService.getStopById(id));
    }
} 