package io.fleet_vehicles_mgr.repository;

import io.fleet_vehicles_mgr.domain.Vehicle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
public class VehicleRepositoryTest {

    @Autowired
    VehicleRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    private String vehicleMockName() {
        return "cx-30";
    }

    private Vehicle createMock() {
        var vehicle = new Vehicle(null,vehicleMockName(),"KLMD2234","JLS584","RED",5,4);
        return vehicle;
    }
    private Vehicle modifiedMock() {
        var vehicle = new Vehicle(null,vehicleMockName(),"ABC25543","MCV005","BLUE",6,8);
        return vehicle;
    }

    @Test
    void t01_CreateVehicle() {
        var vehicle = createMock();
        var persistentVehicle = repository.save(vehicle);
        assertNotNull(persistentVehicle);
    }
    @Test
    void t02_FindVehicle() {
        var vehicle = createMock();
        var persistentVehicle = repository.save(vehicle);

        var vehicles = repository.findByName(vehicleMockName());
        assertNotNull(vehicles);
        assertEquals(1, vehicles.size());
    }

    @Test
    void t03_UpdateVehicle() {

        // create vehicle
        var vehicle = createMock();
        var persistentVehicle_t0 = repository.save(vehicle);
        // update it
        var modifiedVehicle = modifiedMock();

        // not included in coverage-report because fo instrospection usage
        // BeanUtils.copyProperties(modifiedVehicle,persistentVehicle_t0,"id");
        persistentVehicle_t0.setColor(modifiedVehicle.getColor());
        persistentVehicle_t0.setNumberOfDoors(modifiedVehicle.getNumberOfDoors());
        persistentVehicle_t0.setNumberOfWheels(modifiedVehicle.getNumberOfWheels());
        persistentVehicle_t0.setLicensePlateNumber(modifiedVehicle.getLicensePlateNumber());
        persistentVehicle_t0.setVIN(modifiedVehicle.getVIN());


        var persistentVehicle_t1 = repository.save(persistentVehicle_t0);

        // retrieve it
        var vehicleName = vehicleMockName();
        var vehicles = repository.findByName(vehicleName);

        assertNotNull(vehicle);
        assertEquals(1, vehicles.size());
        assertEquals(modifiedVehicle.getColor(), vehicles.get(0).getColor());
        assertEquals(modifiedVehicle.getNumberOfDoors(), vehicles.get(0).getNumberOfDoors());
        assertEquals(modifiedVehicle.getNumberOfWheels(), vehicles.get(0).getNumberOfWheels());
        assertEquals(modifiedVehicle.getLicensePlateNumber(), vehicles.get(0).getLicensePlateNumber());
        assertEquals(modifiedVehicle.getVIN(), vehicles.get(0).getVIN());

    }

    @Test
    void t04_FindAllVehicles() {
        var vehicle = createMock();
        var persistentVehicle = repository.save(vehicle);

        var vehicleList = repository.findAll();
        assertEquals(true, vehicleList.size() > 0);
    }
}
