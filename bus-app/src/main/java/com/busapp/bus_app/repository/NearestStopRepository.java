package com.busapp.bus_app.repository;

import com.busapp.bus_app.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NearestStopRepository extends MongoRepository<Location, String> {
}
