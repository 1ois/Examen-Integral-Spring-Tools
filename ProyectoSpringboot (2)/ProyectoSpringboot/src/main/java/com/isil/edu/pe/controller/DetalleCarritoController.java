package com.isil.edu.pe.controller;

import com.isil.edu.pe.exceptions.ResourceNotFoundException;
import com.isil.edu.pe.model.DetalleCarrito;
import com.isil.edu.pe.repository.DetalleCarritoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/detallecarrito")
@CrossOrigin(origins = "http://localhost:5173") // Permite llamadas desde tu frontend React
public class DetalleCarritoController {

    @Autowired
    private DetalleCarritoRepository detalleRepo;

    // ✅ Obtener todos los detalles
    @GetMapping("/detalles")
    public List<DetalleCarrito> getAllDetalles() {
        return detalleRepo.findAll();
    }

    // ✅ Obtener un detalle por ID
    @GetMapping("/detalle/{id}")
    public ResponseEntity<DetalleCarrito> getDetalleById(@PathVariable("id") Integer iddetalle)
            throws ResourceNotFoundException {
        DetalleCarrito detalle = detalleRepo.findById(iddetalle)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado con ID: " + iddetalle));
        return ResponseEntity.ok(detalle);
    }

    // ✅ Crear un nuevo detalle (POST desde React)
    @PostMapping("/detalle")
    public ResponseEntity<DetalleCarrito> createDetalle(@RequestBody DetalleCarrito detalle) {
        DetalleCarrito savedDetalle = detalleRepo.save(detalle);
        return ResponseEntity.ok(savedDetalle);
    }

    // ✅ Actualizar un detalle existente
    @PutMapping("/detalle/{id}")
    public ResponseEntity<DetalleCarrito> updateDetalle(@PathVariable("id") Integer iddetalle,
                                                        @RequestBody DetalleCarrito detalleRequest)
            throws ResourceNotFoundException {
        DetalleCarrito detalle = detalleRepo.findById(iddetalle)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado con ID: " + iddetalle));

        detalle.setCantidad(detalleRequest.getCantidad());
        detalle.setPrecioUnitario(detalleRequest.getPrecioUnitario());
        detalle.setSubtotal(detalleRequest.getSubtotal());
        detalle.setFechaAgregada(detalleRequest.getFechaAgregada());
        detalle.setEstadoItem(detalleRequest.getEstadoItem());
        detalle.setCarrito(detalleRequest.getCarrito());
        detalle.setProducto(detalleRequest.getProducto());

        DetalleCarrito updatedDetalle = detalleRepo.save(detalle);
        return ResponseEntity.ok(updatedDetalle);
    }

    // ✅ Eliminar un detalle
    @DeleteMapping("/detalle/{id}")
    public Map<String, Boolean> deleteDetalle(@PathVariable("id") Integer iddetalle)
            throws ResourceNotFoundException {
        DetalleCarrito detalle = detalleRepo.findById(iddetalle)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado con ID: " + iddetalle));
        detalleRepo.delete(detalle);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

