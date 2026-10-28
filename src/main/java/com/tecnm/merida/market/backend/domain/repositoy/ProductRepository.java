package com.tecnm.merida.market.backend.domain.repositoy;

import com.tecnm.merida.market.backend.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryID);
    Optional<List<Product>> getScareProducts(int quantity);
    Optional<Product> getProduct (int productId);
    Product save (Product product);
    void delete(int productId);
}
