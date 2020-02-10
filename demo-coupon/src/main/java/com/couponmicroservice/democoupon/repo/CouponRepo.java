package com.couponmicroservice.democoupon.repo;

import com.couponmicroservice.democoupon.models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    Optional<Coupon> findByAccount(String account);
}
