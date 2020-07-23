package com.finartz.study.service;

import com.finartz.study.entity.Airport;

import javax.persistence.EntityNotFoundException;
import java.util.List;


public interface AirportService {

    Airport get(Long id);
    Airport getByName(String name) throws EntityNotFoundException;
    Airport add(Airport airport);
    Airport update(Airport airport);
    void delete(Long id);
    List<Airport> getAll();
}
