package io.fleet_vehicles_mgr.config;

import io.fleet_vehicles_mgr.domain.Vehicle;
import io.fleet_vehicles_mgr.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    VehicleRepository vehicleRepository;

    @Bean
    CommandLineRunner initDatabase(VehicleRepository repository) {
        var vehicle1 = new Vehicle();
        vehicle1.setName("chevrolet aveo gti emotion");
        vehicle1.setLicensePlateNumber("DDR133");
        vehicle1.setColor("GRAY");
        vehicle1.setNumberOfWheels(4);
        vehicle1.setNumberOfDoors(3);
        vehicle1.setVIN("KLM133DDR");

        return args -> {
            log.info("Preloading " + vehicleRepository.save(vehicle1));
        };
    }

}
