package com.equipo.Proyecto_Final.model;

import jakarta.persistence.*;

@Entity
public class ProductoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;
    private int cantidadSeleccionada;
    private double precio;
    private String nombreCliente;

    public ProductoPedido() {}
    public ProductoPedido(Long id, String nombre, String categoria, int cantidadSeleccionada, double precio, String nombreCliente) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidadSeleccionada = cantidadSeleccionada;
        this.precio = precio;
        this.nombreCliente = nombreCliente;
    }

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public int getCantidadSeleccionada() {
        return cantidadSeleccionada;
    }
    public void setCantidadSeleccionada(int cantidadSeleccionada) {
        this.cantidadSeleccionada = cantidadSeleccionada;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
     
}
