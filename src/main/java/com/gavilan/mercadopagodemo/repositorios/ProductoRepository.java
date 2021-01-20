package com.gavilan.mercadopagodemo.repositorios;

import com.gavilan.mercadopagodemo.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
