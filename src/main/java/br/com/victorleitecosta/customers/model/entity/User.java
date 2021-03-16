package br.com.victorleitecosta.customers.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotEmpty(message = "{field.login.required}")
    private String username;

    @Column
    @NotEmpty(message = "{field.password.required}")
    private String password;

}
