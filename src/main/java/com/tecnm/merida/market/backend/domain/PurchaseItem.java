package com.tecnm.merida.market.backend.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

public class PurchaseItem {

    @JsonAlias("idProducto")
    private int productId;

    @JsonAlias("cantidad")
    private int quantity;

    private double total;

    @JsonAlias("estado")
    private boolean active;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
