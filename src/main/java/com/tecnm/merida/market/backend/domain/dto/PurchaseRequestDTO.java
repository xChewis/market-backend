package com.tecnm.merida.market.backend.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

//DTO complejo (maestro-detalle): cabecera del ticket + lista de productos adquiridos
public class PurchaseRequestDTO {

    @Schema(example = "CLI-001")
    private String idCliente;

    @Schema(example = "TARJETA")
    private String medioPago;

    @Schema(example = "Entregar por la tarde")
    private String comentario;

    private List<PurchaseItemRequestDTO> productosAdquiridos;

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<PurchaseItemRequestDTO> getProductosAdquiridos() {
        return productosAdquiridos;
    }

    public void setProductosAdquiridos(List<PurchaseItemRequestDTO> productosAdquiridos) {
        this.productosAdquiridos = productosAdquiridos;
    }
}
