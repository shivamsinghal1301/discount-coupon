package com.task.shivam.controller;

import com.task.shivam.model.couponDiscountDto;
import com.task.shivam.entity.cart;
import com.task.shivam.entity.coupon;
import com.task.shivam.service.couponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class couponController {

    private final couponService couponService;

    public couponController(couponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    public ResponseEntity<coupon> createCoupon(@RequestBody coupon coupon) {
        return ResponseEntity.ok(couponService.saveCoupon(coupon));
    }

    @GetMapping
    public ResponseEntity<List<coupon>> getAllCoupons() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<coupon> getCouponById(@PathVariable Long id) {
        return couponService.getCouponById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<coupon> updateCoupon(@PathVariable Long id, @RequestBody coupon coupon) {
        return ResponseEntity.ok(couponService.updateCoupon(id, coupon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/applicable-coupons")
    public ResponseEntity<List<couponDiscountDto>> getApplicableCoupons(@RequestBody cart cart) {
        return ResponseEntity.ok(couponService.getApplicableCoupons(cart));
    }

    @PostMapping("/apply-coupon/{id}")
    public ResponseEntity<cart> applyCoupon(@PathVariable Long id, @RequestBody cart cart) {
        return ResponseEntity.ok(couponService.applyCouponToCart(id, cart));
    }

}

