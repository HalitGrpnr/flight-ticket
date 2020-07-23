package com.finartz.study.service;

import com.finartz.study.entity.Flight;
import com.finartz.study.entity.Ticket;
import com.finartz.study.repository.TicketRepository;
import com.finartz.study.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    FlightService flightService;

    @Override
    public Ticket get(Long id) {
        return ticketRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public Ticket add(Ticket ticket) {
        Flight flight = flightService.get(ticket.getFlight().getId());
        ticket.setFlight(flight);
        try {
            ticket.setCreditCardNumber(CommonUtils.maskCreditCardNumber(ticket.getCreditCardNumber()));
        } catch (InvalidParameterException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

}
