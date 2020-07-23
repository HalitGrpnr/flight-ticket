package com.finartz.study.repository;

import com.finartz.study.entity.Airport;
import com.finartz.study.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByDeparture(Airport departure);
    List<Route> findByDestination(Airport destination);
    List<Route> findByDepartureAndDestination(Airport departure, Airport destination);
}
