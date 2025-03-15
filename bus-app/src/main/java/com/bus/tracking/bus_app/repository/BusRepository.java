package com.bus.tracking.bus_app.repository;

import com.bus.tracking.bus_app.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, String> {
} 