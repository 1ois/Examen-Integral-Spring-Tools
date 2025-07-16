package com.isil.edu.pe.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="carritodecompras")
public class CarritoDeCompras {
	
	private Integer idcarrito;
	private Usuarios usuarios;
	private Date fechacreacion;
	private String estado;
	
	public CarritoDeCompras () {}

	public CarritoDeCompras(Integer idcarrito, Usuarios usuarios, Date fechacreacion, String estado) {
		super();
		this.idcarrito = idcarrito;
		this.usuarios = usuarios;
		this.fechacreacion = fechacreacion;
		this.estado = estado;
	}
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getIdcarrito() {
		return idcarrito;
	}

	public void setIdcarrito(Integer idcarrito) {
		this.idcarrito = idcarrito;
	}
	
	@ManyToOne
	@JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
		
		
		
	}
	@Column(name="fechacreacion", nullable = false)
	public Date getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	@Column(name="estado", nullable = false)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	};
	
	

}
