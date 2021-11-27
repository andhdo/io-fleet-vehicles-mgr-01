package io.fleet_vehicles_mgr.web.rest;

import io.fleet_vehicles_mgr.domain.Vehicle;
import io.fleet_vehicles_mgr.repository.VehicleRepository;
import io.fleet_vehicles_mgr.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech_.web.util.HeaderUtil;
import tech_.web.util.ResponseUtil;

import javax.print.attribute.standard.Media;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Vehicle}.
 */
@RestController
@CrossOrigin(origins = "*") // or a filter, or security-config (https://spring.io/blog/2015/06/08/cors-support-in-spring-framework)
@RequestMapping(
    name = "/api",
    consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
    produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
)
//@Transactional
public class VehicleResource {

    private final Logger log = LoggerFactory.getLogger(VehicleResource.class);

    private static final String ENTITY_NAME = "vehicle";

    @Value("${this.clientApp.name}")
    private String applicationName;

    private final VehicleRepository vehicleRepository;

    public VehicleResource(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) throws URISyntaxException {
        log.debug("REST request to save Vehicle : {}", vehicle);
        if (vehicle.getId() != null) {
            throw new BadRequestAlertException("A new vehicle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Vehicle vehicleCreated = vehicleRepository.save(vehicle);
        return new ResponseEntity<>(vehicleCreated, HttpStatus.CREATED);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String id) {
        log.debug("REST request to get Vehicle : {}", id);
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vehicle);
    }


    @PutMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable(value = "id", required = false) final String id,
            @RequestBody Vehicle vehicle
    ) throws URISyntaxException {
        log.debug("REST request to update Vehicle : {}, {}", id, vehicle);
        if (vehicle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!vehicleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        Vehicle result = vehicleRepository.save(vehicle);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vehicle.getId()))
                .body(result);
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        log.debug("REST request to get all Vehicles");
        return vehicleRepository.findAll();
    }


    /**
     * {@code DELETE  /vehicles} : remove specific vehicle.
     */
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Delete user " + id);
        vehicleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
