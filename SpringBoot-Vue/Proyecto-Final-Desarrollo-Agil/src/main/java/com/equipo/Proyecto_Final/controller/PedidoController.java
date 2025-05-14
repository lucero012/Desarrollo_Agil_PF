package com.equipo.Proyecto_Final.controller;

import com.equipo.Proyecto_Final.dto.PedidoDTO;
import com.equipo.Proyecto_Final.model.ProductoPedido;
import com.equipo.Proyecto_Final.repository.ProductoPedidoRepository;
import com.equipo.Proyecto_Final.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*") 
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProductoPedidoRepository productoPedidoRepository;

    
    @PostMapping("/guardar")
    public ResponseEntity<String> guardarPedido(@RequestBody PedidoDTO pedidoDTO) {
        try {
            pedidoService.guardarPedido(pedidoDTO);
            return ResponseEntity.ok("Pedido guardado correctamente");
        } catch (Exception e) {
           
            return ResponseEntity.status(500).body("Error al guardar el pedido: " + e.getMessage());
        }
    }

    
    @GetMapping("/traer_pedido")
    public ResponseEntity<List<ProductoPedido>> traerPedidos() {
        List<ProductoPedido> pedidos = productoPedidoRepository.findAll();
        return ResponseEntity.ok(pedidos);
    }
    @DeleteMapping("/eliminar_pedido/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        if (!productoPedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productoPedidoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
