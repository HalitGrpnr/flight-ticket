package com.finartz.study.repository;

import com.finartz.study.entity.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TicketRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void whenFindById_thenReturnTicket() {
        Airport departure = Airport.builder().name("Trabzon").build();
        Airport destination = Airport.builder().name("Sabiha").build();

        entityManager.persist(departure);
        entityManager.persist(destination);

        Route route = Route.builder().departure(departure).destination(destination).build();
        entityManager.persist(route);

        Company company = Company.builder().name("THY").build();
        entityManager.persist(company);

        Flight flight = Flight.builder().company(company).route(route).seatNumber(200).price(100.0).build();
        entityManager.persist(flight);

        Ticket ticket = Ticket.builder().flight(flight).status(TicketStatus.ORDERED).
                creditCardNumber("1111-2222-3333-4444").build();

        entityManager.persist(ticket);
        entityManager.flush();

        // when
        Optional<Ticket> fetchedTicket = ticketRepository.findById(1L);

        // then
        assertTrue(fetchedTicket.isPresent());
        assertEquals(ticket.getId(), fetchedTicket.get().getId());
    }

}