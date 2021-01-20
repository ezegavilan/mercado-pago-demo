package com.gavilan.mercadopagodemo.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 917239172L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DetallePedido> items;

    public Pedido() {

    }

    public Pedido(List<DetallePedido> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DetallePedido> getItems() {
        return items;
    }

    public void setItems(List<DetallePedido> items) {
        this.items = items;
    }

    public Double getTotal() {
        Double total = 0.00;

        for (DetallePedido item: this.items) {
            total += item.getSubtotal();
        }

        return total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", items=" + items +
                '}';
    }
}
