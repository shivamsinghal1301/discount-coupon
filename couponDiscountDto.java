package com.task.shivam.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class couponDiscountDto {
    private Long couponId;
    private String couponCode;
    private double discountAmount;
}
