package com.bus.tracking.bus_app.controller;

import com.bus.tracking.bus_app.dto.TicketDto;
import com.bus.tracking.bus_app.model.Bus;
import com.bus.tracking.bus_app.model.Route;
import com.bus.tracking.bus_app.model.Ticket;
import com.bus.tracking.bus_app.service.BusService;
import com.bus.tracking.bus_app.service.JwtService;
import com.bus.tracking.bus_app.service.RouteService;
import com.bus.tracking.bus_app.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BusService busService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/buses")
    public ResponseEntity<List<Bus>> getAllBuses() {
        return ResponseEntity.ok(busService.getAllBuses());
    }

    @GetMapping("/buses/{busId}/routes")
    public ResponseEntity<List<Route>> getRoutesByBusId(@PathVariable String busId) {
        return ResponseEntity.ok(routeService.getRoutesByBusId(busId));
    }

    @PostMapping("/ticket")
    public ResponseEntity<?> bookTicket(@RequestBody TicketDto ticketDto, @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7); // Remove "Bearer " prefix
            String passengerId = jwtService.extractUserId(token);
            
            Ticket ticket = ticketService.bookTicket(ticketDto, passengerId);
            return ResponseEntity.ok(ticket);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getTickets(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // Remove "Bearer " prefix
        String passengerId = jwtService.extractUserId(token);
        return ResponseEntity.ok(ticketService.getTicketsByPassengerId(passengerId));
    }
} 