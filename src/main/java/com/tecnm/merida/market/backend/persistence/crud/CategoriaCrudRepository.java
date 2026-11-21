package com.tecnm.merida.market.backend.persistence.crud;

import com.tecnm.merida.market.backend.persistence.entity.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer> {
}
