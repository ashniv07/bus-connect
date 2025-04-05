package com.busapp.bus_app.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Ticket {
    private String ticketId;
    private String busNo;
    private String source;
    private String destination;
    private LocalDateTime issuedAt;
}
