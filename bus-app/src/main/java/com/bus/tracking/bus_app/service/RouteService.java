package com.bus.tracking.bus_app.service;

import com.bus.tracking.bus_app.model.*;
import com.bus.tracking.bus_app.repository.RouteRepository;
import com.bus.tracking.bus_app.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private BusService busService;

    public Route createRoute(Route route) {
        // Validate stops are in order
        List<Stop> stops = route.getStops();
        for (int i = 0; i < stops.size(); i++) {
            Stop stop = stops.get(i);
            stop.setStopOrder(i + 1);
            stop.setRoute(route);
        }
        return routeRepository.save(route);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public List<Route> getRoutesByBusId(String busId) {
        return routeRepository.findByBusId(busId);
    }

    public Route getRouteById(String id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + id));
    }

    public void addDummyRoutes() {
        if (routeRepository.count() == 0) {
            List<Bus> buses = busService.getAllBuses();
            if (!buses.isEmpty()) {
                // Create minimal dummy routes with stops
                String[][] routes = {
                    {"New York", "Boston"},
                    {"Boston", "Washington DC"}
                };

                for (int i = 0; i < routes.length && i < buses.size(); i++) {
                    Route route = new Route();
                    route.setName(routes[i][0] + " to " + routes[i][1]);
                    
                    List<Stop> stops = new ArrayList<>();
                    
                    // Create start stop
                    Stop startStop = new Stop();
                    startStop.setName(routes[i][0]);
                    startStop.setLocation(routes[i][0] + " Station");
                    stops.add(startStop);
                    
                    // Create end stop
                    Stop endStop = new Stop();
                    endStop.setName(routes[i][1]);
                    endStop.setLocation(routes[i][1] + " Station");
                    stops.add(endStop);
                    
                    route.setStops(stops);
                    createRoute(route);
                    
                    // Update bus with route
                    Bus bus = buses.get(i);
                    bus.setRoute(route);
                    busService.updateBus(bus);
                }
            }
        }
    }
} 