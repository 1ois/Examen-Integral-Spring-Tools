package com.isil.edu.pe.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.isil.edu.pe.exceptions.ResourceNotFoundException;
import com.isil.edu.pe.model.Pedido;
import com.isil.edu.pe.repository.PedidoRepository;

@RestController
@RequestMapping("/pedido/controller")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/pedido") 
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable(value = "id") Integer pedidoId)
            throws ResourceNotFoundException {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + pedidoId));
        return ResponseEntity.ok().body(pedido);
    }

    @PostMapping("/pedido")
    public Pedido createPedido(@Validated @RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @PutMapping("/pedido/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable(value = "id") Integer pedidoId,
                                               @Validated @RequestBody Pedido pedidoDetails)
            throws ResourceNotFoundException {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + pedidoId));

        pedido.setCarrito(pedidoDetails.getCarrito());
       
        pedido.setFechapedido(pedidoDetails.getFechapedido());
        pedido.setMontototal(pedidoDetails.getMontototal());
        pedido.setEstado(pedidoDetails.getEstado());

        final Pedido updatedPedido = pedidoRepository.save(pedido);
        return ResponseEntity.ok(updatedPedido);
    }

    @DeleteMapping("/pedido/{id}")
    public Map<String, Boolean> deletePedido(@PathVariable(value = "id") Integer pedidoId)
            throws ResourceNotFoundException {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + pedidoId));

        pedidoRepository.delete(pedido);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
