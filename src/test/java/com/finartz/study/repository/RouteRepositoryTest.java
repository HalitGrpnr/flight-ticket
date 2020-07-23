package com.finartz.study.repository;

import com.finartz.study.entity.Airport;
import com.finartz.study.entity.Route;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RouteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RouteRepository routeRepository;

    @Test
    void whenFindByName_thenReturnRoute() {

        // given
        Airport departure = Airport.builder().name("Trabzon").build();
        Airport destination = Airport.builder().name("Sabiha").build();

        entityManager.persist(departure);
        entityManager.persist(destination);
        entityManager.flush();

        Route route = Route.builder().departure(departure).destination(destination).build();
        entityManager.persist(route);
        entityManager.flush();

        // when
        Route fetchedRoute = routeRepository.getOne(route.getId());

        // then
        assertEquals(fetchedRoute.getId(), route.getId());

    }
}