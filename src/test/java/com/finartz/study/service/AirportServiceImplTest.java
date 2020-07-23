package com.finartz.study.service;

import com.finartz.study.entity.Airport;
import com.finartz.study.entity.Company;
import com.finartz.study.repository.AirportRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AirportServiceImplTest {

    @Autowired
    private AirportService airportService;

    @MockBean
    private AirportRepository airportRepository;

    @Test
    void whenGiveValidName_thenReturnAirport() {
        String name = "Sabiha Gokcen";

        Airport airport = Airport.builder().name(name).build();
        Mockito.when(airportRepository.findByName(airport.getName())).thenReturn(airport);

        Airport fetchedAirport = airportService.getByName(name);

        Assertions.assertEquals(fetchedAirport.getName(), name);

    }

    @Test
    void whenGiveInvalidName_thenReturnEntityNotFoundException() {
        Airport airport = Airport.builder().name("Sabiha Gokcen").build();
        Mockito.when(airportRepository.findByName(airport.getName())).thenReturn(airport);

        assertThrows(EntityNotFoundException.class, () -> airportService.getByName("Trabzon"));

    }

    @Test
    void whenFetchAll_thenReturnAllRecords(){
        Airport airport1 = Airport.builder().name("Trabzon").build();
        Airport airport2 = Airport.builder().name("Ankara").build();

        List<Airport> airportList = Arrays.asList(airport1, airport2);
        Mockito.when(airportRepository.findAll()).thenReturn(airportList);

        List<Airport> fetchedList = airportService.getAll();
        Assertions.assertEquals(airportList.size(), fetchedList.size());
    }

    @Test
    void whenAddAirport_thenReturnSavedRecord() {
        Airport airport = Airport.builder().name("Trabzon").build();
        Mockito.doReturn(airport).when(airportRepository).save(ArgumentMatchers.any());

        Airport returnedAirport = airportService.add(airport);

        assertEquals(airport.getName(), airport.getName());
    }
}