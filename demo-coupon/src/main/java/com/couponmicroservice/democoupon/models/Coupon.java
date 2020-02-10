package com.couponmicroservice.democoupon.models;

import lombok.*;

import javax.persistence.*;

@Table(name="coupon")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Getter @Setter
    private String code;

    @Getter @Setter
    private String account;
}
