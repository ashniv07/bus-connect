package com.bus.tracking.bus_app.repository;

import com.bus.tracking.bus_app.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, String> {
    List<Route> findByBusId(String busId);
} 