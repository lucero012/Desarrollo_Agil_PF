package com.equipo.Proyecto_Final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.equipo.Proyecto_Final.model.Producto;
import com.equipo.Proyecto_Final.repository.ProductoRepository;
import com.equipo.Proyecto_Final.services.ProductoServices;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoServices productoServices;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/traer_productos")
    public ResponseEntity<List<Producto>> traerProductos() {
        List<Producto> todos = productoRepository.findAll();
        return ResponseEntity.ok(todos);
    }

    @PostMapping("/insertar_productos")
    public ResponseEntity<String> guardarProducto(@RequestBody Producto producto) {
        productoServices.guardarProducto(producto);
        return ResponseEntity.status(201).body("Producto creado correctamente");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> actualizarProducto(@RequestBody Producto producto, @PathVariable Long id) {
        Producto existente = productoServices.obtenerProducto_(id);
        existente.setNombre(producto.getNombre());
        existente.setPrecio(producto.getPrecio());
        existente.setCantidad(producto.getCantidad());
        existente.setImagenURL(producto.getImagenURL());
        existente.setCategoria(producto.getCategoria()); // ✅ NUEVO
        productoServices.guardarProducto(existente);
        return ResponseEntity.ok("Producto con ID " + id + " actualizado correctamente");
    }

    @DeleteMapping("/eliminar-productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

   @GetMapping("/categoria/{categoria}") 
public List<Producto> obtenerPorCategoria(@PathVariable String categoria) {
    System.out.println(">>> Buscando productos por categoría: " + categoria);
    return productoRepository.findByCategoriaIgnoreCase(categoria);
}


}
