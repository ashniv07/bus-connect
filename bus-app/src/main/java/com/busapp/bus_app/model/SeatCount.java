package com.busapp.bus_app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "seatCounts")
public class SeatCount {

    @Id
    private String id;
    private String busNo;
    private int currentCount;
    private int capacity;
    private String status;
    private List<Ticket> tickets;
}
