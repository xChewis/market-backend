package com.tecnm.merida.market.backend.domain.repositoy;

import com.tecnm.merida.market.backend.domain.Purchase;
import com.tecnm.merida.market.backend.persistence.entity.Compra;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save (Purchase purchase);
}
