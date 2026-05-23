package com.ufg.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ufg.demojpa.model.Perfil;

public interface IPerfilesRepository extends JpaRepository<Perfil, Integer> {
}