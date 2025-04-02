package com.busapp.bus_app.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busapp.bus_app.DTO.FareResponse;
import com.busapp.bus_app.model.Location;
import com.busapp.bus_app.repository.LocationRepository;

import java.util.Optional;

@Service
public class FareCalculationService {
    @Autowired
    private LocationRepository locationRepository;

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in km
    }

    public double getFare(double distance) {
        if (distance <= 2) return 10;
        else if (distance <= 5) return 20;
        else if (distance <= 10) return 30;
        else if (distance <= 20) return 50;
        else return 100;
    }

    public FareResponse calculateFare(String source, String destination) {
        Location src = locationRepository.findByStopname(source)
                .orElseThrow(() -> new RuntimeException("Source stop not found"));
        Location dest = locationRepository.findByStopname(destination)
                .orElseThrow(() -> new RuntimeException("Destination stop not found"));
        
        double distance = calculateDistance(src.getLat(), src.getLon(), dest.getLat(), dest.getLon());
        double fare = getFare(distance);
        return new FareResponse(distance, fare);
    }
}
