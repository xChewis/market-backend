package com.tecnm.merida.market.backend.persistence.crud;

import com.tecnm.merida.market.backend.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//Métodos abstractos que después se implementarán
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    /*SQL Query Method
    SELECT *
    FROM productos
    WHERE id_categoria = 10?
    ORDER BY nombre ASC
     */
    List<Producto> findByIdCategoriaOrderByNombreAsc (int idCategoria);

    //Cantidad stock
    Optional<List<Producto>> findByCantidadStockLessThenAndEstado (int cantidadStock, boolean estado);


}