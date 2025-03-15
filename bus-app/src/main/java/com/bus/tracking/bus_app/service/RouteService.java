package com.bus.tracking.bus_app.service;

import com.bus.tracking.bus_app.dto.RouteDto;
import com.bus.tracking.bus_app.model.Bus;
import com.bus.tracking.bus_app.model.Route;
import com.bus.tracking.bus_app.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusService busService;

    public Route addRoute(RouteDto routeDto) {
        Route route = new Route();
        route.setSource(routeDto.getSource());
        route.setDestination(routeDto.getDestination());
        route.setDistance(routeDto.getDistance());
        route.setBus(busService.getBusById(routeDto.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found")));
        return routeRepository.save(route);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public List<Route> getRoutesByBusId(String busId) {
        return routeRepository.findByBusId(busId);
    }

    public Optional<Route> getRouteById(String id) {
        return routeRepository.findById(id);
    }

    public void addDummyRoutes() {
        if (routeRepository.count() == 0) {
            List<Bus> buses = busService.getAllBuses();
            if (!buses.isEmpty()) {
                RouteDto[] dummyRoutes = {
                    new RouteDto("New York", "Boston", 300, buses.get(0).getId()),
                    new RouteDto("Boston", "Washington DC", 450, buses.get(0).getId()),
                    new RouteDto("Chicago", "Detroit", 280, buses.get(1).getId()),
                    new RouteDto("Detroit", "Toronto", 380, buses.get(1).getId()),
                    new RouteDto("Los Angeles", "San Francisco", 380, buses.get(2).getId()),
                    new RouteDto("San Francisco", "Seattle", 680, buses.get(2).getId()),
                    new RouteDto("Miami", "Orlando", 230, buses.get(3).getId()),
                    new RouteDto("Orlando", "Atlanta", 440, buses.get(3).getId()),
                    new RouteDto("Dallas", "Houston", 240, buses.get(4).getId()),
                    new RouteDto("Houston", "New Orleans", 350, buses.get(4).getId())
                };

                for (RouteDto routeDto : dummyRoutes) {
                    addRoute(routeDto);
                }
            }
        }
    }
} 