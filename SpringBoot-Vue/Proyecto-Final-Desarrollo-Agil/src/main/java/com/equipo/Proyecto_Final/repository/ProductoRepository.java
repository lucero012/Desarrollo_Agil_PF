package com.equipo.Proyecto_Final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equipo.Proyecto_Final.model.Producto;

public interface ProductoRepository  extends JpaRepository <Producto, Long>{
List<Producto> findByCategoriaIgnoreCase(String categoria);
}
