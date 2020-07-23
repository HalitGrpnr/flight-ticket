package com.finartz.study.repository;

import com.finartz.study.entity.Airport;
import com.finartz.study.entity.Company;
import com.finartz.study.entity.Flight;
import com.finartz.study.entity.Route;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class FlightRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void whenFindByName_thenReturnFlight() {

        // given
        Airport departure = Airport.builder().name("Trabzon").build();
        Airport destination = Airport.builder().name("Sabiha").build();

        entityManager.persist(departure);
        entityManager.persist(destination);

        Route route = Route.builder().departure(departure).destination(destination).build();
        entityManager.persist(route);

        Company company = Company.builder().name("THY").build();
        entityManager.persist(company);

        Flight flight = Flight.builder().company(company).route(route).price(100.0).seatNumber(200).build();
        entityManager.persist(flight);
        entityManager.flush();

        // when
        Optional<Flight> fetchedFlight = flightRepository.findById(1L);

        // then
        //assertEquals(flight.getId(), fetchedFlight.get().getId());

    }
}