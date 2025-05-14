package com.equipo.Proyecto_Final.dto;

import com.equipo.Proyecto_Final.model.ProductoPedido;
import java.util.List;

public class PedidoDTO {
    private Long id;
    private String nombre;
    private List<ProductoPedido> productos;

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<ProductoPedido> getProductos() { return productos; }
    public void setProductos(List<ProductoPedido> productos) { this.productos = productos; }
}
