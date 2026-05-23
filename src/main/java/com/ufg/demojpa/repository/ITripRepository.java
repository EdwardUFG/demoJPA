package com.ufg.demojpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ufg.demojpa.model.Trip;

public interface ITripRepository extends JpaRepository<Trip, Integer> {
    
    List<Trip> findByEstatus(String estatus);
    List<Trip> findByEstatusAndDestacado(String estatus, Integer destacado);
    List<Trip> findByCostoBetween(Double costoMin, Double costoMax);
    
    // Busca trips cuyo estatus coincida con una lista de opciones
    List<Trip> findByEstatusIn(List<String> estatus);
    
    // Busca por estatus y los ordena por ID de forma descendente
    List<Trip> findByEstatusOrderByIdDesc(String estatus);
}