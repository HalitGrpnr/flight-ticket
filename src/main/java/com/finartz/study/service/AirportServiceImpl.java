package com.finartz.study.service;

import com.finartz.study.entity.Airport;
import com.finartz.study.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    AirportRepository airportRepository;

    @Override
    public Airport get(Long id) {
        return airportRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Airport getByName(String name) throws EntityNotFoundException{
        Airport airport = airportRepository.findByName(name);

        if (airport == null){
            throw new EntityNotFoundException("Airport Not Found");
        }

        return airport;
    }

    @Override
    public Airport add(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport update(Airport airport) {
        try {
            Airport fetchedAirport = get(airport.getId());
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Airport Not Found", e);
        }

        return airportRepository.save(airport);
    }

    @Override
    public void delete(Long id) {
        airportRepository.deleteById(id);
    }

    @Override
    public List<Airport> getAll() {
        return airportRepository.findAll();
    }
}
