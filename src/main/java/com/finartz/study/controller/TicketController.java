package com.finartz.study.controller;

import com.finartz.study.entity.Ticket;
import com.finartz.study.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAll(){
        return ResponseEntity.ok(ticketService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> get(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(ticketService.get(id));
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Ticket Not Found", e);
        }
    }

    @PostMapping
    public ResponseEntity<Ticket> add(@RequestBody Ticket ticket){
        return ResponseEntity.ok(ticketService.add(ticket));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        ticketService.delete(id);
    }

    @PutMapping
    public ResponseEntity<Ticket> update(@RequestBody Ticket ticket){
        return ResponseEntity.ok(ticketService.update(ticket));
    }
}
