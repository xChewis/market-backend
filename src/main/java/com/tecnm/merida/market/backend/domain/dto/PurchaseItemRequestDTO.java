package com.tecnm.merida.market.backend.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

//Detalle de la compra: cada producto que lleva el cliente
public class PurchaseItemRequestDTO {

    @Schema(example = "1")
    private Integer idProducto;

    @Schema(example = "2")
    private Integer cantidad;

    @Schema(example = "500.0")
    private Double total;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
