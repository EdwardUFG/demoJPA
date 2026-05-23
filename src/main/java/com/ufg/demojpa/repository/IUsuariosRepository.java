package com.ufg.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ufg.demojpa.model.Usuario;

public interface IUsuariosRepository extends JpaRepository<Usuario, Integer> {
}