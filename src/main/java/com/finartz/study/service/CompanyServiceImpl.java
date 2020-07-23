package com.finartz.study.service;

import com.finartz.study.entity.Company;
import com.finartz.study.entity.Flight;
import com.finartz.study.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    FlightService flightService;

    @Override
    public Company get(Long id) {
        return companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Company getByName(String name) {
        Company company = companyRepository.findByName(name);

        if (company == null){
            throw new EntityNotFoundException("Company Not Found");
        }
        return company;
    }

    @Override
    public Company add(Company company) {

        if (company.getFlights() != null && !company.getFlights().isEmpty()){
            for (Flight flight : company.getFlights()){
                try {
                    Flight fetchedFlight = flightService.get(flight.getId());
                } catch (EntityNotFoundException e){
                    flight = flightService.add(flight);
                }
            }
        }

        return companyRepository.save(company);
    }

    @Override
    public Company update(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
