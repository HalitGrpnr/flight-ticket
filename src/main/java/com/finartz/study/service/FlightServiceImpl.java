package com.finartz.study.service;

import com.finartz.study.entity.Company;
import com.finartz.study.entity.Flight;
import com.finartz.study.entity.Route;
import com.finartz.study.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    CompanyService companyService;

    @Autowired
    RouteService routeService;

    @Override
    public Flight get(Long id) {
        return flightRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Flight> getByCompanyName(String companyName) {
        Company company = companyService.getByName(companyName);
        return flightRepository.findByCompany(company);
    }

    @Override
    public Flight add(Flight flight) {
        Company company = companyService.getByName(flight.getCompany().getName());
        Route route = routeService.get(flight.getRoute().getId());

        flight.setCompany(company);
        flight.setRoute(route);
        return flightRepository.save(flight);
    }

    @Override
    public Flight update(Flight flight) {

        if (isFlightCapacityIncreasedAsThreshold(flight)){
            updatePriceWhenFlightCapacityIncreaseAsThreshold(flight);
        }
        return flightRepository.save(flight);
    }

    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @Override
    public void updatePriceWhenFlightCapacityIncreaseAsThreshold(Flight flight) {
        flight.setPrice(flight.getPrice() + (flight.getPrice() / 10));
    }

    private boolean isFlightCapacityIncreasedAsThreshold(Flight updatedFlight) {
        Flight originalFlight = get(updatedFlight.getId());

        if (originalFlight.getSeatNumber() < updatedFlight.getSeatNumber()){
            int diff = updatedFlight.getSeatNumber() - originalFlight.getSeatNumber();
            double rate = (diff * 100) / originalFlight.getSeatNumber();
            return (rate >= 10);
        }
        return false;
    }

}
