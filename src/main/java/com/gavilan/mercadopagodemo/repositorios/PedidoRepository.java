package com.gavilan.mercadopagodemo.repositorios;

import com.gavilan.mercadopagodemo.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
