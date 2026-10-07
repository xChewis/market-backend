package com.tecnm.merida.market.backend.persistence.mapper;

import com.tecnm.merida.market.backend.domain.Category;
import com.tecnm.merida.market.backend.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "Spring")
public interface CategoryMapper {

    @Mappings({
            @Mapping(source =  "idCategoria", target = "categoryId"),
            @Mapping(source =  "descripcion", target = "category"),
            @Mapping(source =  "estado", target = "active"),
    })
Category toCategory(Categoria categoria);
@InheritInverseConfiguration
@Mapping(target = "productos", ignore = true)
Category toCategoria(Category category);
}
