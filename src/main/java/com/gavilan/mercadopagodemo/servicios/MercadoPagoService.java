package com.gavilan.mercadopagodemo.servicios;

import com.gavilan.mercadopagodemo.modelo.Pedido;

public interface MercadoPagoService {

    String crearPago();

    String crearPago(Pedido pedido);
}
