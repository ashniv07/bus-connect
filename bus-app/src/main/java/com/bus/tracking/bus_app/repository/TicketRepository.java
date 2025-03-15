package com.bus.tracking.bus_app.repository;

import com.bus.tracking.bus_app.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findByPassenger_Id(String passengerId);
} 