package com.equipo.Proyecto_Final.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo.Proyecto_Final.model.Producto;
import com.equipo.Proyecto_Final.repository.ProductoRepository;

@Service
public class ProductoServices {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    public void guardarProducto(Producto producto){
        productoRepository.save(producto);
    }

    public Producto obtenerProducto_(Long id){
        return productoRepository.findById(id).get();
    }

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }   


}
