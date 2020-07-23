package com.finartz.study.repository;

import com.finartz.study.entity.Company;
import com.finartz.study.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByCompany(Company company);
}
