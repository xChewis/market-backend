package com.tecnm.merida.market.backend.persistence;

import com.tecnm.merida.market.backend.persistence.crud.ProductoCrudRepository;
import com.tecnm.merida.market.backend.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    //SELECT * FROM productos
    public List<Producto> getAll(){
        //Vamos a "castear"
        return  (List<Producto>) productoCrudRepository.findAll();
    }

    List<Producto> getByCategory(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }
    public Optional <List<Producto>>getEscasos(int cantidad) {
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }
}