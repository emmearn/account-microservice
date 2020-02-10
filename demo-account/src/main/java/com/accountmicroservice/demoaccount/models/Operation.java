package com.accountmicroservice.demoaccount.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "operations")
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    @Id
    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    private String id;

    @NotNull
    @Getter @Setter
    private Date date;

    @NotNull
    @Getter @Setter
    private Double amount;

    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    private String description;

    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    private String sender;

    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    private String receiver;
}