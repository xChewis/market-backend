package com.tecnm.merida.market.backend.persistence;

import com.tecnm.merida.market.backend.domain.Purchase;
import com.tecnm.merida.market.backend.domain.repository.PurchaseRepository;
import com.tecnm.merida.market.backend.persistence.crud.CompraCrudRepository;
import com.tecnm.merida.market.backend.persistence.entity.Compra;
import com.tecnm.merida.market.backend.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll(){
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<Purchase> getPurchase(int purchaseId) {
        return compraCrudRepository.findById(purchaseId).map(mapper::toPurchase);
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(mapper::toPurchases);
    }

    @Override
    public Purchase save(Purchase purchase) {
        //Cabecera (maestro) -> entidad Compra, con su lista de CompraProducto (detalle)
        Compra compra = mapper.toCompra(purchase);
        //Cada detalle debe conocer a su compra para que @MapsId tome el ID autogenerado
        if (compra.getProductos() != null) {
            compra.getProductos().forEach(producto -> producto.setCompra(compra));
        }
        //Un solo save: gracias a CascadeType.ALL Hibernate inserta la compra y luego sus productos
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }

    @Override
    public Optional<Purchase> updateHeader(int purchaseId, String paymentMethod, String comment, String state) {
        return compraCrudRepository.findById(purchaseId).map(compra -> {
            if (paymentMethod != null) compra.setMedioPago(paymentMethod);
            if (comment != null) compra.setComentario(comment);
            if (state != null) compra.setEstado(state);
            return mapper.toPurchase(compraCrudRepository.save(compra));
        });
    }

    @Override
    public void delete(int purchaseId) {
        //CascadeType.ALL también borra los registros de compras_productos
        compraCrudRepository.deleteById(purchaseId);
    }
}
