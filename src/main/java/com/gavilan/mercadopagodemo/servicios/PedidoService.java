package com.gavilan.mercadopagodemo.servicios;

import com.gavilan.mercadopagodemo.modelo.Pedido;

import java.util.List;

public interface PedidoService extends CrudService<Pedido, Long> {

    Pedido crearPedido(Pedido pedido);

    List<Pedido> obtenerPedidos();

    Pedido obtenerPedido(Long pedidoId);
}
