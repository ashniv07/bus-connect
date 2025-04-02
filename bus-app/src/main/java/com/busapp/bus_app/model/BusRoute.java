package com.busapp.bus_app.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.List;

@Document(collection = "busroutes")
@Data
public class BusRoute {
    @Id
    private String id;
    private String busNo;
    private String route;
    private List<String> stops;
}