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

@Entity
@Table(name = "users")
@AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    private Integer id;

    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    private String username;

    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    private String password;

    @NotEmpty @NotNull @NotBlank
    @Getter @Setter
    private String permission;

}
