# discount-coupon

This project is a RESTful API for managing and applying different types of discount coupons in an e-commerce system. 
The system is designed to be flexible, extensible, and easy to integrate with cart/order services.

API-Endpoints

POST         /api/coupons                         Create a new coupon
GET          /api/coupons                         Fetch all coupons
GET          /api/coupon/{id}                     Fetch coupon by Id
PUT          /api/coupon/{id}                     Update coupon
DELETE       /api/coupon/{id}                     Delete coupon
POST         /api/coupons/applicable-coupons      Get all applicable coupons for a cart
POST         /api/coupons/apply-coupon/{id}       Apply a specific coupon and get discounted cart


Coupon Types

1. Cart-wise          --  Applies discount to the entire cart total.
2. Product-wise       --  Applies discount to specific products only.
3. BxGy coupon        --  Implements basic "Buy X, Get Y Free" logic.
4. First-order coupon --  Can only be used by users placing their first order.

Note: This project is made for sample only.
