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
import org.springframework.web.bind.annotation.CrossOrigin;


import com.isil.edu.pe.exceptions.ResourceNotFoundException;

import com.isil.edu.pe.model.Producto;
import com.isil.edu.pe.repository.ProductoRepository;

@RestController
@RequestMapping("/producto/controller")
@CrossOrigin(origins = "http://localhost:5173")  // <-- aquí permites que React acceda

public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;
	
	

	@GetMapping("/producto")
	private List<Producto> getAllProducto() {
		return productoRepository.findAll();
				
	}
	
	@GetMapping("/producto/{id}")
	public ResponseEntity<Producto> getProductoId(@PathVariable(value="id") Long productoId)
	 throws ResourceNotFoundException{
			
		Producto producto = productoRepository.findById(productoId).orElseThrow(()->
		new ResourceNotFoundException("El producto no se encunetra por ID:" + productoId));
		return ResponseEntity.ok(producto);
	}
	
	//creamos un empleado
	
	@PostMapping("/producto") // se usa para crear o guardar en base de datos en memoria
	public Producto createProducto( @Validated @RequestBody Producto producto) {
		return productoRepository.save(producto);
	}
	
	@PutMapping("/producto/{id}") // se usa para actualizar los empleados
	public  ResponseEntity<Producto> updateProducto(@PathVariable(value ="id")Long productoId, @Validated 
			@RequestBody Producto productoDetails) throws ResourceNotFoundException{
		Producto producto = productoRepository.findById(productoId).
				orElseThrow(()-> new ResourceNotFoundException("El Producto no ha sido encontrado por el ID:" + productoId));
		producto.setNombre(productoDetails.getNombre());
		producto.setPrecio(productoDetails.getPrecio());
		producto.setStock(productoDetails.getStock());
		producto.setCategoria(productoDetails.getCategoria());
		producto.setImagen(productoDetails.getImagen());
		final Producto updateProduco = productoRepository.save(producto);
		return ResponseEntity.ok(updateProduco);
		
	}
	@DeleteMapping("/producto/{id}") // se utiliza para eliminar empleador por id
	public Map<String,Boolean> deleteProducto(@PathVariable(value="id") Long productoId)
	throws  ResourceNotFoundException
	{
		Producto producto = productoRepository.findById(productoId).
				orElseThrow(()-> new ResourceNotFoundException("El producto no se encuentra el ID:_" + productoId));
		productoRepository.delete(producto);
		Map<String,Boolean> response = new HashMap <>();
		response.put("delete",Boolean.TRUE);
		return response;
		
	}	
	
	
	
	
	
	
	
	
	
}
