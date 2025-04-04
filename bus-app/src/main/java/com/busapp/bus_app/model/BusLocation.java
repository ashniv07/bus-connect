package com.busapp.bus_app.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Document(collection = "busLocation")
@Entity
@Data
public class BusLocation {
    @Id
    private String busUid;

    private double latitude;
    private double longitude;
    private LocalDateTime updatedAt;
}
