package com.tecnm.merida.market.backend.domain.repositoy;

import com.tecnm.merida.market.backend.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product>getAll();
    Optional<List<Product>> getByCategory(int categoryID);
    Optional<Product> getScarceProducts (int quantity);
    Product save (Product product);
    Product delete (Product product);
}
