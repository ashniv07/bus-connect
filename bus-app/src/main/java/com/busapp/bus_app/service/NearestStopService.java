package com.busapp.bus_app.service;

import com.busapp.bus_app.model.Location;
import com.busapp.bus_app.repository.NearestStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NearestStopService {

    @Autowired
    private NearestStopRepository nearestStopRepo;

    public Location getNearestStop(double userLat, double userLon) {
        List<Location> allStops = nearestStopRepo.findAll();
        Location nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Location stop : allStops) {
            double distance = calculateDistance(userLat, userLon, stop.getLat(), stop.getLon());
            if (distance < minDistance) {
                minDistance = distance;
                nearest = stop;
            }
        }

        return nearest;
    }

    // Haversine Formula
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of Earth in KM
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) *
                Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in KM
    }
}
