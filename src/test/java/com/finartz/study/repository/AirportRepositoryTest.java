package com.finartz.study.repository;

import com.finartz.study.entity.Airport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AirportRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AirportRepository airportRepository;

    @Test
    void whenFindByName_thenReturnAirport() {

        // given
        Airport airport = Airport.builder().name("Sabiha").build();
        entityManager.persist(airport);
        entityManager.flush();

        // when
        Airport fetchedAirport = airportRepository.findByName("Sabiha");

        // then
        assertEquals(fetchedAirport.getName(), airport.getName());

    }
}