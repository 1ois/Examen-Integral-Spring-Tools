package com.isil.edu.pe.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpedido;

    @ManyToOne
    @JoinColumn(name = "idcarrito", referencedColumnName = "idcarrito", nullable = false)
    private CarritoDeCompras carrito;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechapedido", nullable = false)
    private Date fechapedido;

    @Column(name = "montototal", nullable = false)
    private double montototal;

    @Column(name = "estado", nullable = false)
    private String estado;

    public Pedido() {}

    public Pedido(int idpedido, CarritoDeCompras carrito, Date fechapedido, double montototal, String estado) {
        this.idpedido = idpedido;
        this.carrito = carrito;
        this.fechapedido = fechapedido;
        this.montototal = montototal;
        this.estado = estado;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public CarritoDeCompras getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoDeCompras carrito) {
        this.carrito = carrito;
    }

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
    }

    public double getMontototal() {
        return montototal;
    }

    public void setMontototal(double montototal) {
        this.montototal = montototal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idpedido=" + idpedido +
                ", carrito=" + (carrito != null ? carrito.getIdcarrito() : null) +
                ", fechapedido=" + fechapedido +
                ", montototal=" + montototal +
                ", estado='" + estado + '\'' +
                '}';
    }
}