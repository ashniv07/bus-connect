package com.busapp.bus_app.controller;

import com.busapp.bus_app.model.BusRoute;
import com.busapp.bus_app.service.BusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
public class BusRouteController {

    private final BusRouteService busRouteService;

    @Autowired
    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    @GetMapping("/bus/{busNo}")
    public ResponseEntity<?> getBusStops(@PathVariable String busNo) {
        try {
            Optional<BusRoute> busRoute = busRouteService.getBusRouteByNumber(busNo);
            
            if (busRoute.isPresent()) {
                return ResponseEntity.ok(busRoute.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error processing request for bus " + busNo);
        }
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Bus Route API is working! Current time: " + java.time.LocalDateTime.now();
    }
}