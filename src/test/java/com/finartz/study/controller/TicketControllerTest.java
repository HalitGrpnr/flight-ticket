package com.finartz.study.controller;

import com.finartz.study.entity.*;
import com.finartz.study.service.RouteService;
import com.finartz.study.service.TicketService;
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
@WebMvcTest(TicketController.class)
class TicketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;


    @Test
    void whenGetAllFlights_thenReturnFlights() throws Exception {

        Airport departure = Airport.builder().name("Trabzon").build();
        Airport destination = Airport.builder().name("Sabiha").build();

        Route route = Route.builder().departure(departure).destination(destination).id(1L).build();

        Company company = Company.builder().name("THY").build();

        Flight flight = Flight.builder().company(company).route(route).seatNumber(200).price(100.0).build();

        Ticket ticket = Ticket.builder().flight(flight).status(TicketStatus.ORDERED)
                .creditCardNumber("1111-2222-3333-4444").build();

        List<Ticket> tickets = Arrays.asList(ticket);

        Mockito.when(ticketService.getAll()).thenReturn(tickets);

        mockMvc.perform(get("/tickets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].creditCardNumber", Matchers.is("1111-2222-3333-4444")));
    }

    @Test
    void whenGetByTicketId_thenReturnTicket() throws Exception {

        Airport departure = Airport.builder().name("Trabzon").build();
        Airport destination = Airport.builder().name("Sabiha").build();

        Route route = Route.builder().departure(departure).destination(destination).id(1L).build();

        Company company = Company.builder().name("THY").build();

        Flight flight = Flight.builder().company(company).route(route).seatNumber(200).price(100.0).build();
        Ticket ticket = Ticket.builder().flight(flight).status(TicketStatus.ORDERED)
                .creditCardNumber("1111-2222-3333-4444").id(1L).build();

        Mockito.when(ticketService.get(1L)).thenReturn(ticket);

        mockMvc.perform(get("/tickets/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("creditCardNumber", Matchers.is("1111-2222-3333-4444")));
    }
}