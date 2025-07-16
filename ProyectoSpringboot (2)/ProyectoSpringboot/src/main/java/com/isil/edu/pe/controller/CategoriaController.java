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
import com.isil.edu.pe.model.Categoria;
import com.isil.edu.pe.repository.CategoriaReposiroty;

@RestController
@RequestMapping("/categoria/controller")
public class CategoriaController {

	@Autowired
	private CategoriaReposiroty categoriarepository;
	
	
	@GetMapping("/categoria")
	private List<Categoria> getAllCategoria() {
		return categoriarepository.findAll();
				
	}
	
	@GetMapping("/categoria/{id}")
	public ResponseEntity<Categoria> getEmployeesId(@PathVariable(value="id") Long categoriaId)
	 throws ResourceNotFoundException{
			
		Categoria categoria = categoriarepository.findById(categoriaId).orElseThrow(()->
		new ResourceNotFoundException("La categoria no se encunetra por ID:" + categoriaId));
		return ResponseEntity.ok(categoria);
	}
	
	//creamos un empleado
	
	@PostMapping("/categoria") // se usa para crear o guardar en base de datos en memoria
	public Categoria createCategoria( @Validated @RequestBody Categoria categoria) {
		return categoriarepository.save(categoria);
	}
	
	@PutMapping("/categoria/{id}") // se usa para actualizar los empleados
	public  ResponseEntity<Categoria> updateCategoria(@PathVariable(value ="id")Long categoriaId, @Validated 
			@RequestBody Categoria categoriaDetails) throws ResourceNotFoundException{
		Categoria categoria = categoriarepository.findById(categoriaId).
				orElseThrow(()-> new ResourceNotFoundException("La Categoria no ha sido encontrado por el ID:" + categoriaId));
		categoria.setNombre(categoriaDetails.getNombre());
		categoria.setDescripcion(categoriaDetails.getDescripcion());
		final Categoria updateCategoria = categoriarepository.save(categoria);
		return ResponseEntity.ok(updateCategoria);
		
	}
	@DeleteMapping("/categoria/{id}") // se utiliza para eliminar empleador por id
	public Map<String,Boolean> deleteCategoria(@PathVariable(value="id") Long categoriaId)
	throws  ResourceNotFoundException
	{
		Categoria categoria = categoriarepository.findById(categoriaId).
				orElseThrow(()-> new ResourceNotFoundException("La categoria no se encuentra el ID:_" + categoriaId));
		categoriarepository.delete(categoria);
		Map<String,Boolean> response = new HashMap <>();
		response.put("delete",Boolean.TRUE);
		return response;
		
	}
	
	
}
