package com.couponmicroservice.democoupon.services;

public interface CouponService {

    String getAvailableCoupons(String jwt);
}
