package com.couponmicroservice.democoupon.controllers;

import com.couponmicroservice.democoupon.models.JsonResponseBody;
import com.couponmicroservice.democoupon.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class RestController {

    @Autowired
    CouponService couponService;

    @RequestMapping("/coupons")
    public ResponseEntity<JsonResponseBody>findCoupons(@RequestParam String jwt) {
        // couponService.method()
        return null;
    }
}
