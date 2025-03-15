package com.bus.tracking.bus_app.controller;

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
import java.util.Map;

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

    @GetMapping("/buses/{busId}/stops")
    public ResponseEntity<List<Route>> getStopsByBusId(@PathVariable String busId) {
        return ResponseEntity.ok(routeService.getRoutesByBusId(busId));
    }

    @PostMapping("/ticket")
    public ResponseEntity<?> bookTicket(
            @RequestBody Map<String, String> request,
            @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7); // Remove "Bearer " prefix
            String passengerId = jwtService.extractUserId(token);
            
            String busId = request.get("busId");
            String startStopId = request.get("startStopId");
            String endStopId = request.get("endStopId");
            
            if (busId == null || startStopId == null || endStopId == null) {
                return ResponseEntity.badRequest().body("Missing required parameters");
            }
            
            Ticket ticket = ticketService.bookTicket(busId, startStopId, endStopId, passengerId);
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