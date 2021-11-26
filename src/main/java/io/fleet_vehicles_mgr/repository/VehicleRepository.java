package io.fleet_vehicles_mgr.repository;

import io.fleet_vehicles_mgr.domain.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Vehicle entity.
 */
@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
}
