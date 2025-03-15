package com.bus.tracking.bus_app;

import com.bus.tracking.bus_app.service.BusService;
import com.bus.tracking.bus_app.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BusAppApplication implements CommandLineRunner {

	@Autowired
	private BusService busService;

	@Autowired
	private RouteService routeService;

	public static void main(String[] args) {
		
		SpringApplication.run(BusAppApplication.class, args);
		System.out.println("Running");
	}

	@Override
	public void run(String... args) {
		// Add dummy data
		busService.addDummyBuses();
		routeService.addDummyRoutes();
	}

}
