package com.tecnm.merida.market.backend.domain.service;

import com.tecnm.merida.market.backend.domain.Purchase;
import com.tecnm.merida.market.backend.domain.dto.PurchaseRequestDTO;
import com.tecnm.merida.market.backend.domain.dto.PurchaseUpdateDTO;
import com.tecnm.merida.market.backend.domain.repositoy.PurchaseRepository;
import com.tecnm.merida.market.backend.persistence.mapper.PurchaseRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseRequestMapper purchaseRequestMapper;

    public List<Purchase> getAll(){
        return purchaseRepository.getAll();
    }

    public Optional<Purchase> getPurchase(int purchaseId) {
        return purchaseRepository.getPurchase(purchaseId);
    }

    public Optional<List<Purchase>> getByClient(String clientId){
        return purchaseRepository.getByClient(clientId);
    }

    //Todo o nada: si falla el guardado de cualquier producto se hace rollback de la compra completa
    @Transactional
    public Purchase save (PurchaseRequestDTO request){
        //El mapper convierte la cabecera y su lista interna de productos
        Purchase purchase = purchaseRequestMapper.toPurchase(request);
        purchase.setDate(LocalDateTime.now());
        purchase.setState("P");
        //Un solo save: la cascada inserta la compra y después sus productos
        return purchaseRepository.save(purchase);
    }

    @Transactional
    public Optional<Purchase> update(int purchaseId, PurchaseUpdateDTO request) {
        return purchaseRepository.updateHeader(purchaseId,
                request.getMedioPago(), request.getComentario(), request.getEstado());
    }

    @Transactional
    public boolean delete(int purchaseId) {
        if (getPurchase(purchaseId).isPresent()) {
            purchaseRepository.delete(purchaseId);
            return true;
        } else {
            return false;
        }
    }
}
