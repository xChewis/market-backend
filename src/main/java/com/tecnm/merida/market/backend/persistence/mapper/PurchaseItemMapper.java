package com.tecnm.merida.market.backend.persistence.mapper;


import com.tecnm.merida.market.backend.domain.Product;
import com.tecnm.merida.market.backend.domain.PurchaseItem;
import com.tecnm.merida.market.backend.persistence.entity.Compra;
import com.tecnm.merida.market.backend.persistence.entity.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper (componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "estado", target = "active"),
    })
    PurchaseItem toPurchaseItem(CompraProducto compra);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "id.idCompra", ignore = true),
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
    })
    CompraProducto toCompraProducto(PurchaseItem item);
}
