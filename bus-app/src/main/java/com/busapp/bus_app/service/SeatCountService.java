package com.busapp.bus_app.service;

import com.busapp.bus_app.model.SeatCount;
import com.busapp.bus_app.model.Ticket;
import com.busapp.bus_app.repository.SeatCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class SeatCountService {

    @Autowired
    private SeatCountRepository seatCountRepository;

    public SeatCount generateTicket(String busNo, String source, String destination) {
        Optional<SeatCount> seatData = seatCountRepository.findByBusNo(busNo);

        if (seatData.isPresent()) {
            SeatCount seatCount = seatData.get();
            
            // Generate a new ticket
            Ticket newTicket = new Ticket();
            newTicket.setTicketId(UUID.randomUUID().toString());
            newTicket.setBusNo(busNo);
            newTicket.setSource(source);
            newTicket.setDestination(destination);
            newTicket.setIssuedAt(LocalDateTime.now());

            // Update seat count
            seatCount.getTickets().add(newTicket);
            seatCount.setCurrentCount(seatCount.getCurrentCount() + 1);

            // Update status based on count
            if (seatCount.getCurrentCount() >= seatCount.getCapacity()) {
                seatCount.setStatus("Crowded");
            } else {
                seatCount.setStatus("Available");
            }

            return seatCountRepository.save(seatCount);
        } else {
            throw new RuntimeException("Bus not found");
        }
    }

    public SeatCount decreaseSeatCount(String busNo, String ticketId) {
        Optional<SeatCount> seatData = seatCountRepository.findByBusNo(busNo);

        if (seatData.isPresent()) {
            SeatCount seatCount = seatData.get();
            
            // Remove ticket from list
            seatCount.getTickets().removeIf(ticket -> ticket.getTicketId().equals(ticketId));
            
            // Update seat count
            seatCount.setCurrentCount(seatCount.getCurrentCount() - 1);

            // Update status
            if (seatCount.getCurrentCount() >= seatCount.getCapacity()) {
                seatCount.setStatus("Crowded");
            } else {
                seatCount.setStatus("Available");
            }

            return seatCountRepository.save(seatCount);
        } else {
            throw new RuntimeException("Bus not found");
        }
    }
}

