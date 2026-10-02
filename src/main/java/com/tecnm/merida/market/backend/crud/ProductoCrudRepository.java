package com.tecnm.merida.market.backend.crud;

import com.tecnm.merida.market.backend.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
}