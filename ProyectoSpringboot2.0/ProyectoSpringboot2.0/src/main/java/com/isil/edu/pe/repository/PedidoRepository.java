package com.isil.edu.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isil.edu.pe.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
