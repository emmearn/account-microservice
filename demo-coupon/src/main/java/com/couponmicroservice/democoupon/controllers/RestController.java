package com.couponmicroservice.democoupon.controllers;

import com.couponmicroservice.democoupon.models.JsonResponseBody;
import com.couponmicroservice.democoupon.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    CouponService couponService;

    @RequestMapping("/coupon")
    public ResponseEntity<JsonResponseBody>findCoupons(@RequestParam String jwt) {
        try {
            String coupon = couponService.getAvailableCoupons(jwt);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), coupon));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }
}
