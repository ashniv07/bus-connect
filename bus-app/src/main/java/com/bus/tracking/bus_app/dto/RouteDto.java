package com.bus.tracking.bus_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDto {
    private String source;
    private String destination;
    private float distance;
    private String busId;

    public RouteDto() {}

    public RouteDto(String source, String destination, float distance, String busId) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.busId = busId;
    }
} 