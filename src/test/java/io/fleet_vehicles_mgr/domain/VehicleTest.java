package io.fleet_vehicles_mgr.domain;

import io.fleet_vehicles_mgr.web.rest.TestUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class VehicleTest {
    @Test
    void equalsVerifier() throws Exception {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId("id1");
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setId(vehicle1.getId());
        assertThat(vehicle1).isEqualTo(vehicle2);
        vehicle2.setId("id2");
        assertThat(vehicle1).isNotEqualTo(vehicle2);
        vehicle1.setId(null);
        assertThat(vehicle1).isNotEqualTo(vehicle2);
    }}
