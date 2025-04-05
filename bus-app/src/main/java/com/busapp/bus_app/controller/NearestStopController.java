package com.busapp.bus_app.controller;

import com.busapp.bus_app.model.Location;
import com.busapp.bus_app.service.NearestStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")

@RestController
public class NearestStopController {

    @Autowired
    private NearestStopService nearestStopService;

    @GetMapping("/nearest-stop")
    public Location getNearest(@RequestParam double lat, @RequestParam double lon) {
        return nearestStopService.getNearestStop(lat, lon);
    }
}
