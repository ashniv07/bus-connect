package com.bus.tracking.bus_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "stops")
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String name;
    private String location;
    
    @Column(name = "stop_order")
    private int stopOrder;
    
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
} 