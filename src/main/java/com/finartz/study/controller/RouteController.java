package com.finartz.study.controller;

import com.finartz.study.entity.Route;
import com.finartz.study.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    RouteService routeService;

    @GetMapping
    public ResponseEntity<List<Route>> getAll(){
        return ResponseEntity.ok(routeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> get(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(routeService.get(id));
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Route Not Found", e);
        }
    }

    @GetMapping("/departure/{name}")
    public ResponseEntity<List<Route>> getByDeparture(@PathVariable("name") String name){
        List<Route> route = routeService.getByDepartureName(name);

        if (route == null || route.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(route);
    }

    @GetMapping("/destination/{name}")
    public ResponseEntity<List<Route>> getByDestination(@PathVariable("name") String name){
        List<Route> route = routeService.getByDestinationName(name);

        if (route == null || route.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(route);
    }

    @PostMapping
    public ResponseEntity<Route> add(@RequestBody Route route){
        try{
            return ResponseEntity.ok(routeService.add(route));
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Route Not Found", e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        routeService.delete(id);
    }

    @PutMapping
    public ResponseEntity<Route> update(@RequestBody Route route){
        return ResponseEntity.ok(routeService.update(route));
    }
}
