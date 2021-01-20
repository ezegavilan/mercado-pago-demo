package com.gavilan.mercadopagodemo.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pedidos_detalles")
public class DetallePedido implements Serializable {
    private static final long serialVersionUID = 11273891237L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto producto;
    private Integer cantidad;

    public DetallePedido() {

    }

    public DetallePedido(Producto producto, Integer cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return this.cantidad.doubleValue() * this.producto.getPrecio();
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "id=" + id +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                '}';
    }
}
