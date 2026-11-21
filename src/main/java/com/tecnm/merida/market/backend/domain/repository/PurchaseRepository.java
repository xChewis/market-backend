package com.tecnm.merida.market.backend.domain.repositoy;

import com.tecnm.merida.market.backend.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<Purchase> getPurchase(int purchaseId);
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save (Purchase purchase);
    Optional<Purchase> updateHeader(int purchaseId, String paymentMethod, String comment, String state);
    void delete(int purchaseId);
}
