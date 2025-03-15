package com.bus.tracking.bus_app.dto;

import com.bus.tracking.bus_app.model.BusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusDto {
    private String busName;
    private int capacity;
    private BusType type;

    public BusDto() {}

    public BusDto(String busName, int capacity, BusType type) {
        this.busName = busName;
        this.capacity = capacity;
        this.type = type;
    }
} 