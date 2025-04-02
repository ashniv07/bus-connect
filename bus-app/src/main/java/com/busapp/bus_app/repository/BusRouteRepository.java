package com.busapp.bus_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.busapp.bus_app.model.BusRoute;

import java.util.Optional;

@Repository
public interface BusRouteRepository extends MongoRepository<BusRoute, String> {
    Optional<BusRoute> findByBusNo(String busNo);
}