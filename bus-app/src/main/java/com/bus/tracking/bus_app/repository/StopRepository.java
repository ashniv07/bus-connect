package com.bus.tracking.bus_app.repository;

import com.bus.tracking.bus_app.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop, String> {
} 