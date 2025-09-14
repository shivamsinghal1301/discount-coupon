package com.task.shivam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Entity
@Table(name = "bxgy_coupons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class bxGyCoupon extends coupon {

    @ElementCollection
    private Map<Long, Integer> buyProductIdToQty;

    @ElementCollection
    private Map<Long, Integer> getProductIdToQty;

    private Integer maxRepetition;


}
