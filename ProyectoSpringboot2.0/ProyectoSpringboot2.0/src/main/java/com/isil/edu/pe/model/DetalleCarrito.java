package com.isil.edu.pe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "detalle_carrito")
public class DetalleCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddetalle; // Campo autogenerado

    @ManyToOne
    @JoinColumn(name = "idcarrito", nullable = false)
    @JsonIgnoreProperties("detalles") // Evita recursión infinita al serializar
    private CarritoDeCompras carrito;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    @JsonIgnoreProperties("detalles") // Evita recursión infinita al serializar
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private double precioUnitario;

    @Column(name = "subtotal", nullable = false)
    private double subtotal;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd") // <-- Esto permite que React envíe la fecha correctamente
    @Column(name = "fecha_agregada", nullable = false)
    private Date fechaAgregada;

    @Column(name = "estado_item", nullable = false)
    private String estadoItem;

    // Constructor vacío
    public DetalleCarrito() {}

    // Getters y Setters

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public CarritoDeCompras getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoDeCompras carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Date getFechaAgregada() {
        return fechaAgregada;
    }

    public void setFechaAgregada(Date fechaAgregada) {
        this.fechaAgregada = fechaAgregada;
    }

    public String getEstadoItem() {
        return estadoItem;
    }

    public void setEstadoItem(String estadoItem) {
        this.estadoItem = estadoItem;
    }
}
