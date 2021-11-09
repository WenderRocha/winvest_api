package com.wender.dev.winvest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_wallet")
public class Wallet implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo nome é requerido.")
    private String name;

    @NotNull(message = "O campo saldo é requerido.")
    private BigDecimal balance;

    private String imgUrl;

    @NotNull(message = "O campo usuário é requerido.")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Wallet(Long id, String name, BigDecimal amount, String imgUrl, User user) {
        this.id = id;
        this.name = name;
        this.balance = amount;
        this.imgUrl = imgUrl;
        this.user = user;
    }
}
