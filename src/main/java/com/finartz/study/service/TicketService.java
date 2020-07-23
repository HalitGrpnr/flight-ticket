package com.finartz.study.service;

import com.finartz.study.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket get(Long id);
    Ticket add(Ticket ticket);
    Ticket update(Ticket ticket);
    void delete(Long id);
    List<Ticket> getAll();
}
