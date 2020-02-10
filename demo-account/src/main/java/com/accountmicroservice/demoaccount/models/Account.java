package com.accountmicroservice.demoaccount.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    private String id;

    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    @Column(name="fk_user")
    private String fkUser;

    @NotNull
    @Getter @Setter
    private Double amount;
}
