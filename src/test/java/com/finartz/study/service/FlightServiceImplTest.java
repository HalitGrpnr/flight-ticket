package com.finartz.study.service;

import com.finartz.study.entity.Airport;
import com.finartz.study.entity.Company;
import com.finartz.study.entity.Flight;
import com.finartz.study.entity.Route;
import com.finartz.study.repository.AirportRepository;
import com.finartz.study.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceImplTest {

    @Autowired
    private FlightService flightService;

    @MockBean
    private FlightRepository flightRepository;

//
//    @Test
//    void whenAddFlight_thenReturnSavedRecord() {
//        Airport airport1 = Airport.builder().name("Trabzon").build();
//        Airport airport2 = Airport.builder().name("Ankara").build();
//
//        Route route = Route.builder().departure(airport1).destination(airport2).build();
//
//        Company company = Company.builder().name("THY").build();
//
//        Flight flight = Flight.builder().route(route).company(company).price(100.0).seatNumber(200).build();
//
//        Mockito.doReturn(flight).when(flightRepository).save(ArgumentMatchers.any());
//
//        Flight returnedFlight = flightService.add(flight);
//
//        assertEquals(flight.getCompany().getName(), returnedFlight.getCompany().getName());
//    }

//
//    @Test
//    void whenIncreaseFlightCapacityAsThreshold_thenIncreasePrice() {
//        Airport airport1 = Airport.builder().name("Trabzon").build();
//        Airport airport2 = Airport.builder().name("Ankara").build();
//
//        Route route = Route.builder().departure(airport1).destination(airport2).build();
//
//        Company company = Company.builder().name("THY").build();
//
//        Flight flight = Flight.builder().id(1L).route(route).company(company).price(100.0).seatNumber(200).build();
//
//        Mockito.doReturn(flight).when(flightRepository).save(ArgumentMatchers.any());
//        Mockito.doReturn(flight).when(flightRepository).findById(1L);
//
//        Flight returnedFlight = flightService.add(flight);
//
//        returnedFlight.setSeatNumber(220);
//
//        Mockito.doReturn(returnedFlight).when(flightRepository).save(ArgumentMatchers.any());
//
//        Flight updatedFlight = flightService.update(returnedFlight);
//
//        assertEquals(updatedFlight.getSeatNumber(), 200);
//        assertEquals(updatedFlight.getPrice(), 110);
//
//    }
}