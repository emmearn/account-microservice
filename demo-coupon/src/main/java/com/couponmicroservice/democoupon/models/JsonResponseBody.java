package com.couponmicroservice.democoupon.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class JsonResponseBody {
    @Getter @Setter
    private int status;

    @Getter @Setter
    private Object response;
}
