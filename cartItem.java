package com.task.shivam.entity;

import lombok.Data;

@Data
public class cartItem {

    private String productId;
    private int quantity;
    private double unitPrice;

    public cartItem copy() {
        cartItem copy = new cartItem();
        copy.setProductId(this.productId);
        copy.setQuantity(this.quantity);
        copy.setUnitPrice(this.unitPrice);
        return copy;
    }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }
}
