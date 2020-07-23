package com.finartz.study.service;

import com.finartz.study.entity.Route;

import java.util.List;

public interface RouteService {
    Route get(Long id);
    List<Route> getByDepartureName(String departureName);
    List<Route> getByDestinationName(String destinationName);
    List<Route> getByDepartureAndDestinationName(String departureName, String destinationName);
    Route add(Route route);
    Route update(Route route);
    void delete(Long id);
    List<Route> getAll();
}
