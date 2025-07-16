package com.isil.edu.pe.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="producto")
public class Producto {
	
	private long idproducto;
	private String nombre;
	private Double precio;
	private Integer stock;
	private Categoria categoria;
	private String imagen;
	
	public Producto () {}
	
	public Producto(long idproducto, String nombre, Double precio, Integer stock,
			Categoria categoria, String imagen) {
		super();
		this.idproducto = idproducto;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.categoria = categoria;
		this.imagen = imagen;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(long idproducto) {
		this.idproducto = idproducto;
	}
	@Column(name="nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column(name="precio", nullable = false)
	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	@Column(name="stock", nullable = false)
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
    @ManyToOne
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	@Column(name="imagen", nullable = false)
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	

	@Override
	public String toString() {
		return "Producto [idproducto=" + idproducto + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock
				+ ", categoria=" + categoria + ", imagen=" + imagen + "]";
	}

	
	
	
	
	
	
	
	
}
