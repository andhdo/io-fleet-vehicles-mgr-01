package io.fleet_vehicles_mgr.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.fleet_vehicles_mgr.IntegrationTest;
import io.fleet_vehicles_mgr.repository.MockData;
import io.fleet_vehicles_mgr.repository.VehicleRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Similar to unittest but excluding influence of mock objects
 */
@ExtendWith(SpringExtension.class)
@IntegrationTest
@AutoConfigureMockMvc
public class VehicleResourceIT {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VehicleRepository vehicleRepository;

    @AfterEach
    void tearDown() {
        vehicleRepository.deleteAll();
    }

    @Test
    public void t12_CreateNewVehicle_WhenOk() throws Exception {
        // prepare mock
        var vehicle = MockData.createMock();

        // exclude mockito emulation

        String url = "/vehicles";
        var mvcResult = mockMvc
                .perform(
                    post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(vehicle))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        var actualJsonResponseAsString = mvcResult.getResponse().getContentAsString();

        /*
        var savedVehicle = vehicleRepository.findByName(MockData.vehicleMockName()).get(0);
        String expectedJsonResponseAsString = objectMapper.writeValueAsString(savedVehicle);

        assertThat(actualJsonResponseAsString).isEqualToIgnoringWhitespace(expectedJsonResponseAsString);
*/
    }

    @Test
    public void t13_CreateNewVehicle_WhenErr_InvalidAttribute() throws Exception {
        // prepare mock
        var vehicle = MockData.createMock();
        vehicle.setName(null);
        vehicle.setNumberOfDoors(-1);

        // exclude mockito emulation

        String url = "/vehicles";
        var mvcResult = mockMvc
            .perform(
                post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(vehicle))
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.timestamp", is(notNullValue())))
            .andExpect(jsonPath("$.status", is(400)))
            .andExpect(jsonPath("$.errors").isMap())
            .andExpect(jsonPath("$.errors.name", Is.is("must not be null")))
            .andExpect(jsonPath("$.errors.numberOfDoors", Is.is("must be greater than 0")));

        /*
        var savedVehicle = vehicleRepository.findByName(MockData.vehicleMockName()).get(0);
        String expectedJsonResponseAsString = objectMapper.writeValueAsString(savedVehicle);

        assertThat(actualJsonResponseAsString).isEqualToIgnoringWhitespace(expectedJsonResponseAsString);
*/
    }


}