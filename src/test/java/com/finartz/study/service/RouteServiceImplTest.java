package com.finartz.study.service;

import com.finartz.study.entity.Airport;
import com.finartz.study.entity.Route;
import com.finartz.study.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RouteServiceImplTest {

    @Autowired
    RouteService routeService;

    @MockBean
    RouteRepository routeRepository;

    @MockBean
    AirportService airportService;

    @Test
    void whenGiveValidDeparture_thenReturnRoutes() {

        Airport airport1 = Airport.builder().name("Sabiha").build();
        Airport airport2 = Airport.builder().name("Trabzon").build();
        Airport airport3 = Airport.builder().name("Ankara").build();

        Route route1 = Route.builder().departure(airport1).destination(airport2).build();
        Route route2 = Route.builder().departure(airport1).destination(airport3).build();

        List<Route> routeList = Arrays.asList(route1, route2);

        Mockito.doReturn(routeList).when(routeRepository).
                findByDeparture(ArgumentMatchers.any());

         List<Route> fetchedRoutes = routeService.getByDepartureName(airport1.getName());

         assertEquals(routeList.size(), fetchedRoutes.size());
    }

    @Test
    void whenGiveValidDestination_thenReturnRoutes() {

        Airport airport1 = Airport.builder().name("Sabiha").build();
        Airport airport2 = Airport.builder().name("Trabzon").build();
        Airport airport3 = Airport.builder().name("Ankara").build();

        Route route1 = Route.builder().departure(airport1).destination(airport2).build();
        Route route2 = Route.builder().departure(airport3).destination(airport2).build();

        List<Route> routeList = Arrays.asList(route1, route2);

        Mockito.doReturn(routeList).when(routeRepository).
                findByDestination(ArgumentMatchers.any());

        List<Route> fetchedRoutes = routeService.getByDestinationName(airport2.getName());

        assertEquals(routeList.size(), fetchedRoutes.size());
    }

    @Test
    void whenGiveValidDepartureAndDestination_thenReturnRoutes() {

        Airport airport1 = Airport.builder().name("Sabiha").build();
        Airport airport2 = Airport.builder().name("Trabzon").build();

        Route route1 = Route.builder().departure(airport1).destination(airport2).build();

        List<Route> routeList = Arrays.asList(route1);

        Mockito.doReturn(routeList).when(routeRepository).
                findByDepartureAndDestination(ArgumentMatchers.any(), ArgumentMatchers.any());

        List<Route> fetchedRoutes = routeService.getByDepartureAndDestinationName(airport1.getName(), airport2.getName());

        assertEquals(routeList.size(), fetchedRoutes.size());
    }

    @Test
    void whenAddRoute_thenReturnSavedRecord() {
        Airport airport1 = Airport.builder().name("Sabiha").build();
        Airport airport2 = Airport.builder().name("Trabzon").build();

        Route route = Route.builder().departure(airport1).destination(airport2).build();

        Mockito.doReturn(route).when(routeRepository).save(ArgumentMatchers.any());
        Mockito.doReturn(airport1).when(airportService).getByName("Sabiha");
        Mockito.doReturn(airport1).when(airportService).getByName("Trabzon");

        Route returnedRoute = routeService.add(route);

        assertEquals(route.getDeparture().getName(), returnedRoute.getDeparture().getName());
        assertEquals(route.getDestination().getName(), returnedRoute.getDestination().getName());
    }
}