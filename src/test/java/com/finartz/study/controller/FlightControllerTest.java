package com.finartz.study.controller;

import com.finartz.study.entity.Airport;
import com.finartz.study.entity.Company;
import com.finartz.study.entity.Flight;
import com.finartz.study.entity.Route;
import com.finartz.study.service.CompanyService;
import com.finartz.study.service.FlightService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FlightController.class)
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @Test
    void whenGetAllFlights_thenReturnFlights() throws Exception {

        Airport departure = Airport.builder().name("Trabzon").build();
        Airport destination = Airport.builder().name("Sabiha").build();

        Route route = Route.builder().departure(departure).destination(destination).id(1L).build();

        Company company = Company.builder().name("THY").build();

        Flight flight = Flight.builder().company(company).route(route).seatNumber(200).price(100.0).build();

        List<Flight> flights = Arrays.asList(flight);

        Mockito.when(flightService.getAll()).thenReturn(flights);

        mockMvc.perform(get("/flights")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].seatNumber", Matchers.is(flight.getSeatNumber())));
    }

    @Test
    void whenGetByCompanyName_thenReturnFlights() throws Exception {

        Airport departure = Airport.builder().name("Trabzon").build();
        Airport destination = Airport.builder().name("Sabiha").build();

        Route route = Route.builder().departure(departure).destination(destination).id(1L).build();

        Company company = Company.builder().name("THY").build();

        Flight flight = Flight.builder().company(company).route(route).seatNumber(200).price(100.0).build();
        List<Flight> flights = Arrays.asList(flight);

        Mockito.when(flightService.getByCompanyName("THY")).thenReturn(flights);

        mockMvc.perform(get("/flights/company/THY")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].seatNumber", Matchers.is(flight.getSeatNumber())));
    }
}