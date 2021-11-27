package io.fleet_vehicles_mgr.repository;

import io.fleet_vehicles_mgr.domain.Vehicle;

public class MockData {
    public static String vehicleMockName() {
        return "cx-30";
    }
    public static Vehicle createMock() {
        var vehicle = new Vehicle(null,vehicleMockName(),"KLMD2234","JLS584","RED",5,4);
        return vehicle;
    }
    public static Vehicle modifiedMock() {
        var vehicle = new Vehicle(null,vehicleMockName(),"ABC25543","MCV005","BLUE",6,8);
        return vehicle;
    }
}
