package com.tecnm.merida.market.backend.persistence.entity;

import com.tecnm.merida.market.backend.persistence.crud.ProductoCrudRepository;
import com.tecnm.merida.market.backend.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    //SELECT * FROM productos
    public List<Producto> getAll(){
        //Vamos a "castear"
        return  (List<Producto>) productoCrudRepository.findAll();
    }
}