package com.busapp.bus_app.repository;

import com.busapp.bus_app.model.SeatCount;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface SeatCountRepository extends MongoRepository<SeatCount, String> {
    Optional<SeatCount> findByBusNo(String busNo);
}
