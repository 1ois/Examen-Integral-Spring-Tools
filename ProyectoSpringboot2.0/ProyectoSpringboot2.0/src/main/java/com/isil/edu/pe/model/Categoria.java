package com.isil.edu.pe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Categoria {

	private long idcategoria;
	private String nombre;
	private String descripcion;
	
	private Categoria () {}

	public Categoria(long idcategoria, String nombre, String descripcion) {
		super();
		this.idcategoria = idcategoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(long idcategoria) {
		this.idcategoria = idcategoria;
	}

	@Column(name="nombre",nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name="descripcion",nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Categoria [idcategoria=" + idcategoria + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
	
	
}
