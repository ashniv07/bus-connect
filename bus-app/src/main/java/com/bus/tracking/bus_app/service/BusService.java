package com.bus.tracking.bus_app.service;

import com.bus.tracking.bus_app.dto.BusDto;
import com.bus.tracking.bus_app.model.Bus;
import com.bus.tracking.bus_app.model.BusType;
import com.bus.tracking.bus_app.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public Bus addBus(BusDto busDto) {
        Bus bus = new Bus();
        bus.setBusName(busDto.getBusName());
        bus.setCapacity(busDto.getCapacity());
        bus.setType(busDto.getType());
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Optional<Bus> getBusById(String id) {
        return busRepository.findById(id);
    }

    public void addDummyBuses() {
        if (busRepository.count() == 0) {
            BusDto[] dummyBuses = {
                new BusDto("Express Deluxe", 50, BusType.EXPRESS),
                new BusDto("Luxury Coach", 40, BusType.LUXURY),
                new BusDto("Regular Bus", 60, BusType.REGULAR),
                new BusDto("City Express", 45, BusType.EXPRESS),
                new BusDto("Comfort Plus", 55, BusType.LUXURY)
            };

            for (BusDto busDto : dummyBuses) {
                addBus(busDto);
            }
        }
    }
} 