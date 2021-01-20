package com.gavilan.mercadopagodemo.controladores;

import com.gavilan.mercadopagodemo.excepciones.DemoException;
import com.gavilan.mercadopagodemo.modelo.Pedido;
import com.gavilan.mercadopagodemo.servicios.MercadoPagoService;
import com.gavilan.mercadopagodemo.servicios.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PedidosController {

    private final PedidoService pedidoService;
    private final MercadoPagoService mercadoPagoService;

    @Autowired
    public PedidosController(PedidoService pedidoService, MercadoPagoService mercadoPagoService) {
        this.pedidoService = pedidoService;
        this.mercadoPagoService = mercadoPagoService;
    }

    @PostMapping("/pedidos")
    public ResponseEntity<Map<String, Object>> crearNuevoPedido(@RequestBody Pedido pedido) {
        Map<String, Object> response = new HashMap<>();
        Pedido nuevoPedido;
        String approveUrl;

        try {
            nuevoPedido = pedidoService.crearPedido(pedido);
            approveUrl = mercadoPagoService.crearPago(pedido);
        } catch (DemoException e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("pedido", nuevoPedido);
        response.put("approveUrl", approveUrl);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
}
