package com.gavilan.mercadopagodemo.servicios;

import com.gavilan.mercadopagodemo.excepciones.DemoException;
import com.gavilan.mercadopagodemo.modelo.DetallePedido;
import com.gavilan.mercadopagodemo.modelo.Pedido;
import com.gavilan.mercadopagodemo.modelo.Producto;
import com.gavilan.mercadopagodemo.repositorios.PedidoRepository;
import com.gavilan.mercadopagodemo.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    @Override
    public Pedido save(Pedido object) {
        return pedidoRepository.save(object);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Pedido findById(Long aLong) {
        return pedidoRepository.findById(aLong)
                .orElseThrow(() -> new DemoException("No existe el pedido con id: " + aLong));
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setItems(pedido.getItems());
        nuevoPedido.setCreatedAt(new Date());

        for (DetallePedido item: nuevoPedido.getItems()) {
            Producto producto = getProducto(item.getProducto().getId());
            item.setProducto(producto);
        }

        return this.save(nuevoPedido);
    }

    @Override
    public List<Pedido> obtenerPedidos() {
        return this.findAll();
    }

    @Override
    public Pedido obtenerPedido(Long pedidoId) {
        return this.findById(pedidoId);
    }

    private Producto getProducto(Long productoId) {
        return productoRepository.findById(productoId)
                .orElseThrow(() -> new DemoException("Producto no existente con id: " + productoId));
    }
}
