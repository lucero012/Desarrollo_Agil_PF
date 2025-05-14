package com.equipo.Proyecto_Final.repository;

import com.equipo.Proyecto_Final.model.ProductoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoPedidoRepository extends JpaRepository<ProductoPedido, Long> {
}
