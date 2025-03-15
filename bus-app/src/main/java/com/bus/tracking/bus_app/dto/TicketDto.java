package com.bus.tracking.bus_app.dto;

import com.bus.tracking.bus_app.model.TicketStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {
    private String busId;
    private String routeId;
    private float price;
    private TicketStatus status;
} 