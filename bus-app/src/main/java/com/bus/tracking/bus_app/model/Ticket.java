package com.bus.tracking.bus_app.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private User passenger;
    
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
    
    @ManyToOne
    @JoinColumn(name = "start_stop_id")
    private Stop startStop;
    
    @ManyToOne
    @JoinColumn(name = "end_stop_id")
    private Stop endStop;
    
    private float price;
    
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
} 