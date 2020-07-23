package com.finartz.study.controller;

import com.finartz.study.entity.Airport;
import com.finartz.study.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    AirportService airportService;

    @GetMapping
    public ResponseEntity<List<Airport>> getAll(){
        return ResponseEntity.ok(airportService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> get(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(airportService.get(id));
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Airport Not Found", e);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Airport> getByName(@PathVariable("name") String name){

        try{
            return ResponseEntity.ok(airportService.getByName(name));
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<Airport> add(@RequestBody Airport airport){
        return ResponseEntity.ok(airportService.add(airport));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        airportService.delete(id);
    }

    @PutMapping
    public ResponseEntity<Airport> update(@RequestBody Airport airport){
        return ResponseEntity.ok(airportService.update(airport));
    }
}
