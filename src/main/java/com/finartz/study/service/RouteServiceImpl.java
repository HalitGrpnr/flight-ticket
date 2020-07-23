package com.finartz.study.service;

import com.finartz.study.entity.Airport;
import com.finartz.study.entity.Route;
import com.finartz.study.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    AirportService airportService;

    @Override
    public Route get(Long id) {
        return routeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Route> getByDepartureName(String departureName) {
        Airport departure = airportService.getByName(departureName);
        return routeRepository.findByDeparture(departure);
    }

    @Override
    public List<Route> getByDestinationName(String destinationName) {
        Airport destination = airportService.getByName(destinationName);
        return routeRepository.findByDestination(destination);
    }

    @Override
    public List<Route> getByDepartureAndDestinationName(String departureName, String destinationName) {
        Airport departure = airportService.getByName(departureName);
        Airport destination = airportService.getByName(destinationName);
        return routeRepository.findByDepartureAndDestination(departure, destination);
    }

    @Override
    public Route add(Route route) {

        if (route.getDeparture() == null || route.getDestination() == null){
            throw new InvalidParameterException("Departure and Destination should be defined");
        }

        Airport departure = airportService.getByName(route.getDeparture().getName());
        Airport destination = airportService.getByName(route.getDestination().getName());

        route.setDeparture(departure);
        route.setDestination(destination);
        return routeRepository.save(route);
    }

    @Override
    public Route update(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public void delete(Long id) {
        routeRepository.deleteById(id);
    }

    @Override
    public List<Route> getAll() {
        return routeRepository.findAll();
    }
}
