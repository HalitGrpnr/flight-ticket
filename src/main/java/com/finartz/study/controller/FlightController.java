package com.finartz.study.controller;

import com.finartz.study.entity.Flight;
import com.finartz.study.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping
    public ResponseEntity<List<Flight>> getAll(){
        return ResponseEntity.ok(flightService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> get(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(flightService.get(id));
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Flight Not Found", e);
        }
    }

    @GetMapping("/company/{name}")
    public ResponseEntity<List<Flight>> getByDeparture(@PathVariable("name") String name){
        List<Flight> flight = flightService.getByCompanyName(name);

        if (flight == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(flight);
    }


    @PostMapping
    public ResponseEntity<Flight> add(@RequestBody Flight flight){
        return ResponseEntity.ok(flightService.add(flight));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        flightService.delete(id);
    }

    @PutMapping
    public ResponseEntity<Flight> update(@RequestBody Flight flight){
        return ResponseEntity.ok(flightService.update(flight));
    }
}
