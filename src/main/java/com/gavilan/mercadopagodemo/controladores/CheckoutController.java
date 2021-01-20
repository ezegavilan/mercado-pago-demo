package com.gavilan.mercadopagodemo.controladores;

import com.gavilan.mercadopagodemo.excepciones.DemoException;
import com.gavilan.mercadopagodemo.servicios.MercadoPagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CheckoutController {

    private final MercadoPagoService mercadoPagoService;

    public CheckoutController(MercadoPagoService mercadoPagoService) {
        this.mercadoPagoService = mercadoPagoService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, Object>> crearPago() {
        Map<String, Object> response = new HashMap<>();
        String approveUrl;

        try {
            approveUrl = this.mercadoPagoService.crearPago();
        } catch (DemoException e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("approveUrl", approveUrl);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
