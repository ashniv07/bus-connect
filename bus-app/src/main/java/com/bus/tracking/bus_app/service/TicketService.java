package com.bus.tracking.bus_app.service;

import com.bus.tracking.bus_app.dto.TicketDto;
import com.bus.tracking.bus_app.model.Bus;
import com.bus.tracking.bus_app.model.Route;
import com.bus.tracking.bus_app.model.Ticket;
import com.bus.tracking.bus_app.model.TicketStatus;
import com.bus.tracking.bus_app.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BusService busService;

    @Autowired
    private RouteService routeService;

    public Ticket bookTicket(TicketDto ticketDto, String passengerId) {
        // Validate bus and route
        Bus bus = busService.getBusById(ticketDto.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        Route route = routeService.getRouteById(ticketDto.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        // Calculate price based on distance and bus type
        float basePrice = route.getDistance() * 0.1f; // $0.10 per km
        float busTypeMultiplier = switch (bus.getType()) {
            case LUXURY -> 1.5f;
            case EXPRESS -> 1.2f;
            case REGULAR -> 1.0f;
        };
        float finalPrice = basePrice * busTypeMultiplier;

        // Create ticket
        Ticket ticket = new Ticket();
        ticket.setPassengerId(passengerId);
        ticket.setBus(bus);
        ticket.setRoute(route);
        ticket.setPrice(finalPrice);
        ticket.setStatus(TicketStatus.PENDING);

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketsByPassengerId(String passengerId) {
        return ticketRepository.findByPassengerId(passengerId);
    }

    public Optional<Ticket> getTicketById(String id) {
        return ticketRepository.findById(id);
    }

    public Ticket updateTicketStatus(String ticketId, TicketStatus status) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }
} 