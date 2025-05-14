package com.equipo.Proyecto_Final.services;

import com.equipo.Proyecto_Final.model.ProductoPedido;
import com.equipo.Proyecto_Final.repository.ProductoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.equipo.Proyecto_Final.dto.PedidoDTO;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private ProductoPedidoRepository productoPedidoRepository;

    // Método para guardar el pedido
    public void guardarPedido(PedidoDTO pedidoDTO) {
        String nombre = pedidoDTO.getNombre();
        List<ProductoPedido> productos = pedidoDTO.getProductos();

        // Guardar el pedido y asociar el nombre a cada producto
        for (ProductoPedido prod : productos) {
            prod.setNombreCliente(nombre); // si agregas ese campo en ProductoPedido
            productoPedidoRepository.save(prod);
        }
    }

    // Método para traer todos los pedidos
    public List<ProductoPedido> traerTodos() {
        return productoPedidoRepository.findAll(); // Devuelve todos los productos pedidos
    }
}
