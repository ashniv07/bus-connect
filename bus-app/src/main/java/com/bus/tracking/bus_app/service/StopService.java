package com.bus.tracking.bus_app.service;

import com.bus.tracking.bus_app.model.Stop;
import com.bus.tracking.bus_app.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopService {

    @Autowired
    private StopRepository stopRepository;

    public Stop createStop(Stop stop) {
        return stopRepository.save(stop);
    }

    public List<Stop> getAllStops() {
        return stopRepository.findAll();
    }

    public Stop getStopById(String id) {
        return stopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stop not found with id: " + id));
    }
} 