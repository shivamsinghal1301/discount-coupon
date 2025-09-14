package com.task.shivam.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class cart {

    private String userId;
    private List<cartItem> items = new ArrayList<>();
    private String appliedCouponCode;
    private double totalDiscount;

    public double getTotalAmount() {
        return items.stream()
                .mapToDouble(cartItem::getTotalPrice)
                .sum();
    }

    public cart copy() {
        cart copy = new cart();
        copy.setUserId(this.userId);
        copy.setAppliedCouponCode(this.appliedCouponCode);
        copy.setTotalDiscount(this.totalDiscount);
        for (cartItem item : this.items) {
            copy.getItems().add(item.copy());
        }
        return copy;
    }

}
