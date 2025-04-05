package com.busapp.bus_app.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busapp.bus_app.DTO.BusLocationDto;
import com.busapp.bus_app.model.BusLocation;
import com.busapp.bus_app.repository.BusLocationRepo;

@Service
public class BusLocationService {

    @Autowired
    private BusLocationRepo repo;

    public void updateBusLocation(BusLocationDto dto) {
        BusLocation location = repo.findById(dto.getBusUid()).orElse(new BusLocation());
        location.setBusUid(dto.getBusUid());
        location.setLatitude(dto.getLatitude());
        location.setLongitude(dto.getLongitude());
        location.setUpdatedAt(LocalDateTime.now());
        repo.save(location);
    }
}
