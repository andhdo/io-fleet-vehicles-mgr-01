package io.fleet_vehicles_mgr.service.impl;

import io.fleet_vehicles_mgr.domain.Vehicle;
import io.fleet_vehicles_mgr.repository.VehicleRepository;
import io.fleet_vehicles_mgr.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Vehicle}.
 */
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    private final Logger log = LoggerFactory.getLogger(VehicleServiceImpl.class);

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        log.debug("Request to save Vehicle : {}", vehicle);
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicle> findOne(String id) {
        log.debug("Request to get Vehicle : {}", id);
        return vehicleRepository.findById(id);
    }

    @Override
    public List<Vehicle> findAll() {
        log.debug("Request to get all Vehicle");
        return vehicleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Vehicle> findAll(Pageable pageable) {
        log.debug("Request to get all Vehicle, paginated");
        return vehicleRepository.findAll(pageable);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Vehicle : {}", id);
        vehicleRepository.deleteById(id);

    }
}
