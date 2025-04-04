package com.busapp.bus_app.DTO;

import lombok.Data;

@Data
public class BusLocationDto {
    private String busUid;
    private double latitude;
    private double longitude;
}
