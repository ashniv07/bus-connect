package com.busapp.bus_app.controller;


import com.busapp.bus_app.model.SeatCount;
import com.busapp.bus_app.service.SeatCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class SeatController {

    @Autowired
    private SeatCountService seatCountService;

    @PostMapping("/generate-ticket")
    public SeatCount generateTicket(@RequestParam String busNo,
                                    @RequestParam String source,
                                    @RequestParam String destination) {
        return seatCountService.generateTicket(busNo, source, destination);
    }

    @PostMapping("/decrease-count")
    public SeatCount decreaseSeatCount(@RequestParam String busNo, @RequestParam String ticketId) {
        return seatCountService.decreaseSeatCount(busNo, ticketId);
    }
}
