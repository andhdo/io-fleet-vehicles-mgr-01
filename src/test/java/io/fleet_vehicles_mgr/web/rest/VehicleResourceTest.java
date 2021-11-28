package io.fleet_vehicles_mgr.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.fleet_vehicles_mgr.domain.Vehicle;
import io.fleet_vehicles_mgr.repository.MockData;
import io.fleet_vehicles_mgr.repository.VehicleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;


import java.util.List;

@WebMvcTest(VehicleResource.class)
public class VehicleResourceTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VehicleRepository vehicleRepository;

    @Test
    public void t01_ListVehicles_WhenOk() throws Exception {
        // prepare mock
        var listVehicles = List.of(
                MockData.createMock(),
                MockData.createMock()
        );

        Mockito.when(vehicleRepository.findAll()).thenReturn(listVehicles);

        String url = "/vehicles";
        var mvcResult = mockMvc
            .perform(
                get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andReturn();
        var actualJsonResponseAsString = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponseAsString = objectMapper.writeValueAsString(listVehicles);

        assertThat(actualJsonResponseAsString).isEqualToIgnoringWhitespace(expectedJsonResponseAsString);


    }

    @Test
    public void t02_CreateNewVehicle_WhenOk() throws Exception {
        // prepare mock
        var vehicle = MockData.createMock();
        var savedVehicle = MockData.createMock();
        savedVehicle.setId("_01");

        Mockito.when(vehicleRepository.save(vehicle)).thenReturn(savedVehicle);

        String url = "/vehicles";
        var mvcResult = mockMvc
            .perform(
                post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicle))
                .with(csrf())
            )
            .andExpect(status().isCreated())
            .andReturn();
        var actualJsonResponseAsString = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponseAsString = objectMapper.writeValueAsString(savedVehicle);

        assertThat(actualJsonResponseAsString).isEqualToIgnoringWhitespace(expectedJsonResponseAsString);

    }




}
