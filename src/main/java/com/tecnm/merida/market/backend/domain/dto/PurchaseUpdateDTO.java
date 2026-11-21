package com.tecnm.merida.market.backend.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

//Datos de la cabecera que se pueden modificar en una compra existente
public class PurchaseUpdateDTO {

    @Schema(example = "EFECTIVO")
    private String medioPago;

    @Schema(example = "Entregar en la mañana")
    private String comentario;

    @Schema(example = "E")
    private String estado;

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
