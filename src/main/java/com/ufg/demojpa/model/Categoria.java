package com.ufg.demojpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria") // Nombre exacto de la tabla en tu base de datos
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia autoincremental
    private Integer id;
    
    private String nombre;
    private String descripcion;

    // Métodos Getter y Setter para 'id'
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Métodos Getter y Setter para 'nombre'
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos Getter y Setter para 'descripcion'
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}