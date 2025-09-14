package com.task.shivam.service;

import com.task.shivam.model.couponDiscountDto;
import com.task.shivam.entity.cart;
import com.task.shivam.entity.cartItem;
import com.task.shivam.entity.coupon;
import com.task.shivam.repository.couponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class couponService {

    @Autowired
    private couponRepository couponRepository;

    @Autowired
    private orderHistoryService orderHistoryService;

    public coupon saveCoupon(coupon coupon) {
        return couponRepository.save(coupon);
    }

    public coupon updateCoupon(Long id, coupon updatedCoupon) {
        coupon existing = couponRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Coupon not found"));
        updatedCoupon.setId(id);
        return couponRepository.save(updatedCoupon);
    }

    public Optional<coupon> getCouponById(Long id) {
        return couponRepository.findById(id);
    }

    public List<coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public void deleteCoupon(Long id) {
        if (!couponRepository.existsById(id)) {
            throw new NoSuchElementException("Coupon not found");
        }
        couponRepository.deleteById(id);
    }

    public List<couponDiscountDto> getApplicableCoupons(cart cart) {
        List<coupon> coupons = couponRepository.findAll();
        List<couponDiscountDto> result = new ArrayList<>();

        for (coupon coupon : coupons) {
            if (isApplicable(coupon, cart)) {
                double discount = calculateDiscount(coupon, cart);
                result.add(new couponDiscountDto(coupon.getId(), coupon.getCode(), discount));
            }
        }

        return result;
    }

    public cart applyCouponToCart(Long id, cart cart) {
        coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Coupon not found"));

        if (!isApplicable(coupon, cart)) {
            throw new IllegalArgumentException("Coupon is not applicable");
        }

        double discount = calculateDiscount(coupon, cart);
        cart.setAppliedCouponCode(coupon.getCode());
        cart.setTotalDiscount(discount);
        return cart;
    }

    private boolean isApplicable(coupon coupon, cart cart) {
        if (coupon.getExpiryDate() != null && coupon.getExpiryDate().isBefore(LocalDate.now())) {
            return false;
        }

        if (coupon.getMinCartAmount() != null && cart.getTotalAmount() < coupon.getMinCartAmount()) {
            return false;
        }

        if (Boolean.TRUE.equals(coupon.getFirstOrderOnly())) {
            boolean isFirstOrder = !orderHistoryService.hasUserPlacedOrder(cart.getUserId());
            if (!isFirstOrder) {
                return false;
            }
        }

        return true;
    }

    private double calculateDiscount(coupon coupon, cart cart) {
        double discount = 0;

        switch (coupon.getType()) {
            case CART_WISE -> {
                discount = (coupon.getDiscountPercent() / 100.0) * cart.getTotalAmount();
            }

            case PRODUCT_WISE -> {
                for (cartItem item : cart.getItems()) {
                    if (item.getProductId().equalsIgnoreCase("listed product")) {
                        discount += (coupon.getDiscountPercent() / 100.0) * item.getTotalPrice();
                    }
                }
            }

            case BXGY -> {
                for (cartItem item : cart.getItems()) {
                    int eligibleFreeItems = item.getQuantity() / 3;
                    discount += eligibleFreeItems * item.getUnitPrice();
                }
            }

            default -> discount = 0;
        }

        if (coupon.getMaxDiscountAmount() != null) {
            discount = Math.min(discount, coupon.getMaxDiscountAmount());
        }

        return discount;
    }
}

