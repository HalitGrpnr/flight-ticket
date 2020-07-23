package com.finartz.study.service;

import com.finartz.study.entity.Flight;

import java.util.List;

public interface FlightService {
    Flight get(Long id);
    List<Flight> getByCompanyName(String companyName);
    Flight add(Flight flight);
    Flight update(Flight flight);
    void delete(Long id);
    List<Flight> getAll();

    void updatePriceWhenFlightCapacityIncreaseAsThreshold(Flight flight);
}
