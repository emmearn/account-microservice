package com.accountmicroservice.models;

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
    private Integer id;

    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    private Date date;

    @NotEmpty @NotNull @NotBlank
    private Double amount;

    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    private String description;

    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    private Integer sender;

    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    private Integer receiver;

}