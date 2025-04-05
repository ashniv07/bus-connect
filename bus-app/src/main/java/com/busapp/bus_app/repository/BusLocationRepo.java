package com.busapp.bus_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.busapp.bus_app.model.BusLocation;

@Repository
public interface BusLocationRepo extends MongoRepository<BusLocation, String> {
}
