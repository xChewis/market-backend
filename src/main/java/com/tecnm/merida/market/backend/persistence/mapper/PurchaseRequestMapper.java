package com.tecnm.merida.market.backend.persistence.mapper;

import com.tecnm.merida.market.backend.domain.Purchase;
import com.tecnm.merida.market.backend.domain.PurchaseItem;
import com.tecnm.merida.market.backend.domain.dto.PurchaseItemRequestDTO;
import com.tecnm.merida.market.backend.domain.dto.PurchaseRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//Convierte el JSON anidado que llega desde Swagger al objeto de dominio Purchase
@Mapper(componentModel = "spring")
public interface PurchaseRequestMapper {

    @Mappings({
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "productosAdquiridos", target = "items"),
            @Mapping(target = "purchaseId", ignore = true),
            @Mapping(target = "date", ignore = true),
            @Mapping(target = "state", ignore = true)
    })
    Purchase toPurchase(PurchaseRequestDTO dto);

    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "total", target = "total"),
            @Mapping(target = "active", constant = "true")
    })
    PurchaseItem toPurchaseItem(PurchaseItemRequestDTO dto);
}
