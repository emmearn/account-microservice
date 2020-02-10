package com.couponmicroservice.democoupon.services.impl;

import com.couponmicroservice.democoupon.models.Coupon;
import com.couponmicroservice.democoupon.models.JsonResponseBody;
import com.couponmicroservice.democoupon.repo.CouponRepo;
import com.couponmicroservice.democoupon.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponRepo couponRepo;

    @Override
    public String getAvailableCoupons(String jwt) {
        List<LinkedHashMap> accounts = getAccountsGivenJwt(jwt);
        StringBuilder availableCoupons = new StringBuilder();

        if(accounts != null && accounts.size() > 0) {
            for (LinkedHashMap account : accounts) {
                String accId = (String) account.get("id");
                Optional<Coupon> optCoupon = couponRepo.findByAccount((String) account.get("id"));

                if (optCoupon.isPresent())
                    availableCoupons.append("Account: " + accId + " coupon: " + optCoupon.get().getCode());
            }
            return availableCoupons.toString();
        }

        return "no tengo coupons";
    }

    private List<LinkedHashMap> getAccountsGivenJwt(String jwt) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("jwt", jwt);
        HttpEntity<?> request = new HttpEntity<>(String.class, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonResponseBody> responseEntity = restTemplate.exchange("http://localhost:8094/accounts/user", HttpMethod.POST, request, JsonResponseBody.class);

        return (List) responseEntity.getBody().getResponse();
    }
}
