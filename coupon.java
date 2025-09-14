package com.task.shivam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "coupons")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private couponType type;

    private LocalDate expiryDate;

    private Double discountPercent;
    private Double maxDiscountAmount;
    private Double minCartAmount;

    private Boolean firstOrderOnly = false;
    private Boolean exclusive = true;

    private Integer maxUsagePerUser;
    private Integer maxTotalUsage;


}
