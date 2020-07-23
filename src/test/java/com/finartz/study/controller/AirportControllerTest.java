package com.finartz.study.controller;

import com.finartz.study.entity.Airport;
import com.finartz.study.entity.Company;
import com.finartz.study.service.AirportService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AirportController.class)
class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @Test
    void whenGetAllAirports_thenReturnAirports() throws Exception {

        Airport airport = Airport.builder().name("Trabzon").build();

        List<Airport> airports = Arrays.asList(airport);

        Mockito.when(airportService.getAll()).thenReturn(airports);

        mockMvc.perform(get("/airports")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is(airport.getName())));
    }

    @Test
    void whenGetByName_thenReturnAirport() throws Exception {

        Airport airport = Airport.builder().name("Trabzon").build();

        Mockito.when(airportService.getByName("Trabzon")).thenReturn(airport);

        mockMvc.perform(get("/airports/name/Trabzon")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}