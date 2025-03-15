package com.bus.tracking.bus_app.service;

import com.bus.tracking.bus_app.model.*;
import com.bus.tracking.bus_app.repository.TicketRepository;
import com.bus.tracking.bus_app.repository.StopRepository;
import com.bus.tracking.bus_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BusService busService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private UserRepository userRepository;

    public Ticket bookTicket(String busId, String startStopId, String endStopId, String passengerId) {
        // Get bus and validate
        Bus bus = busService.getBusById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        
        // Get stops and validate they belong to the bus's route
        Stop startStop = stopRepository.findById(startStopId)
                .orElseThrow(() -> new RuntimeException("Start stop not found"));
        Stop endStop = stopRepository.findById(endStopId)
                .orElseThrow(() -> new RuntimeException("End stop not found"));
        
        if (!startStop.getRoute().getId().equals(bus.getRoute().getId()) ||
            !endStop.getRoute().getId().equals(bus.getRoute().getId())) {
            throw new RuntimeException("Stops do not belong to the selected bus's route");
        }
        
        // Get passenger
        User passenger = userRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        
        // Create ticket with fixed price based on bus type
        float price = calculatePrice(bus.getType());
        
        // Create ticket
        Ticket ticket = new Ticket();
        ticket.setBus(bus);
        ticket.setStartStop(startStop);
        ticket.setEndStop(endStop);
        ticket.setPassenger(passenger);
        ticket.setPrice(price);
        ticket.setStatus(TicketStatus.CONFIRMED);
        ticket.setCreatedAt(LocalDateTime.now());
        
        return ticketRepository.save(ticket);
    }

    private float calculatePrice(BusType busType) {
        switch (busType) {
            case REGULAR:
                return 50.0f;
            case EXPRESS:
                return 75.0f;
            case LUXURY:
                return 100.0f;
            default:
                return 50.0f;
        }
    }

    public List<Ticket> getTicketsByPassengerId(String passengerId) {
        return ticketRepository.findByPassenger_Id(passengerId);
    }
} 