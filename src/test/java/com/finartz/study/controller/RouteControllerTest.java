package com.finartz.study.controller;

import com.finartz.study.entity.Airport;
import com.finartz.study.entity.Route;
import com.finartz.study.service.RouteService;
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
@WebMvcTest(RouteController.class)
class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService routeService;

    @Test
    void whenGetAllRoutes_thenReturnRoutes() throws Exception {

        Airport departure = Airport.builder().name("Trabzon").build();
        Airport destination = Airport.builder().name("Sabiha").build();

        Route route = Route.builder().departure(departure).destination(destination).id(1L).build();

        List<Route> routes = Arrays.asList(route);

        Mockito.when(routeService.getAll()).thenReturn(routes);

        mockMvc.perform(get("/routes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].departure.name", Matchers.is(route.getDeparture().getName())));
    }

    @Test
    void whenGetByName_thenReturnAirport() throws Exception {


        Airport departure = Airport.builder().name("Trabzon").build();
        Airport destination = Airport.builder().name("Sabiha").build();

        Route route = Route.builder().departure(departure).destination(destination).id(1L).build();
        List<Route> routes = Arrays.asList(route);

        Mockito.when(routeService.getByDepartureName("Trabzon")).thenReturn(routes);

        mockMvc.perform(get("/routes/departure/Trabzon")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].departure.name", Matchers.is(route.getDeparture().getName())));
    }
}