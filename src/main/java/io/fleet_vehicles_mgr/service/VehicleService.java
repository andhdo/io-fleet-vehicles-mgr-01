package io.fleet_vehicles_mgr.service;

import io.fleet_vehicles_mgr.domain.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findOne(String id);
    List<Vehicle> findAll();
    Page<Vehicle> findAll(Pageable pageable);
    void delete(String id);
}
