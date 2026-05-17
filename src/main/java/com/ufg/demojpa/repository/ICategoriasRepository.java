package com.ufg.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ufg.demojpa.model.Categoria;

public interface ICategoriasRepository extends JpaRepository<Categoria, Integer> {
}