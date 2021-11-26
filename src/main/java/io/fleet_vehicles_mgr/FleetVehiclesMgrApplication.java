package io.fleet_vehicles_mgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class FleetVehiclesMgrApplication {

	public static void main(String[] args) {
		SpringApplication.run(FleetVehiclesMgrApplication.class, args);
	}

}
