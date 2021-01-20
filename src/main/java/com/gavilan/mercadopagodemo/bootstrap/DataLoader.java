package com.gavilan.mercadopagodemo.bootstrap;

import com.gavilan.mercadopagodemo.modelo.Producto;
import com.gavilan.mercadopagodemo.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    @Autowired
    public DataLoader(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Producto> productos = new ArrayList<>();

        Producto tlou = new Producto();
        tlou.setNombre("The Last of Us Remastered");
        tlou.setMarca("PlayStation/Sony");
        tlou.setPrecio(1799.00);
        productos.add(tlou);

        Producto tlou2 = new Producto("The Last of Us 2", 5900.00, "PlayStation/Sony");
        productos.add(tlou2);

        productoRepository.saveAll(productos);
    }
}
