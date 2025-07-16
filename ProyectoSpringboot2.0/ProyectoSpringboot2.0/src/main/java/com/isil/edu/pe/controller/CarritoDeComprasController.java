package com.isil.edu.pe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isil.edu.pe.exceptions.ResourceNotFoundException;
import com.isil.edu.pe.model.CarritoDeCompras;
import com.isil.edu.pe.repository.CarritoDeComprasRepository;



@RestController
@RequestMapping("/carrito/controller")
public class CarritoDeComprasController {
	
	@Autowired
	private CarritoDeComprasRepository carritoRepository;

	@GetMapping("/carrito")
	private List<CarritoDeCompras> getCarrito() {
		return carritoRepository.findAll();
				
	}
	
	@GetMapping("/carrito/{id}")
	public ResponseEntity<CarritoDeCompras> getCarritoId(@PathVariable(value="id") Integer carritoId)
	 throws ResourceNotFoundException{
			
		CarritoDeCompras carrito = carritoRepository.findById(carritoId).orElseThrow(()->
		new ResourceNotFoundException("El carrito no se encunetra por ID:" + carritoId));
		return ResponseEntity.ok(carrito);
	}
	
	//creamos un carrito
	
	@PostMapping("/carrito") // se usa para crear o guardar en base de datos en memoria
	public CarritoDeCompras createCarrito( @Validated @RequestBody CarritoDeCompras carrito) {
		return carritoRepository.save(carrito);
	}
	
	@PutMapping("/carrito/{id}") // se usa para actualizar los carrito
	public  ResponseEntity<CarritoDeCompras> updateCarrito(@PathVariable(value ="id")Integer carritoId, @Validated 
			@RequestBody CarritoDeCompras carritoDetails) throws ResourceNotFoundException{
		CarritoDeCompras carrito = carritoRepository.findById(carritoId).
				orElseThrow(()-> new ResourceNotFoundException("El Producto no ha sido encontrado por el ID:" + carritoId));
		carrito.setUsuarios(carritoDetails.getUsuarios());
		carrito.setFechacreacion(carritoDetails.getFechacreacion());
		carrito.setEstado(carritoDetails.getEstado());
	
		final CarritoDeCompras updateCarrito = carritoRepository.save(carrito);
		return ResponseEntity.ok(updateCarrito);
		
	}
	@DeleteMapping("/carrito/{id}") // se utiliza para eliminar carrito por id
	public Map<String,Boolean> deleteCarrito(@PathVariable(value="id") Integer carritoId)
	throws  ResourceNotFoundException
	{
		CarritoDeCompras carrito = carritoRepository.findById(carritoId).
				orElseThrow(()-> new ResourceNotFoundException("El carrito no se encuentra el ID:_" + carritoId));
		carritoRepository.delete(carrito);
		Map<String,Boolean> response = new HashMap <>();
		response.put("delete",Boolean.TRUE);
		return response;
		
	}	
	
	
	
}
