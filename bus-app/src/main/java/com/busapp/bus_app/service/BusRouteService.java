package com.busapp.bus_app.service;

import org.springframework.stereotype.Service;

import com.busapp.bus_app.model.BusRoute;
import com.busapp.bus_app.repository.BusRouteRepository;

import java.util.Optional;

@Service
public class BusRouteService {
    private final BusRouteRepository repository;

    public BusRouteService(BusRouteRepository repository) {
        this.repository = repository;
    }

    public Optional<BusRoute> getBusRouteByNumber(String busNo) {
        return repository.findByBusNo(busNo);
    }
}