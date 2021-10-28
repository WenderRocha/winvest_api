package com.wender.dev.winvest.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_management")
public class Management implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal target;

    private Wallet wallet;
    private Operation operation;

    public BigDecimal totalProfit() {
        return wallet.getBalance().add(operation.getTake());
    }

    public BigDecimal profitTake() {
        return this.target.divide(new BigDecimal("100")).setScale(2, RoundingMode.HALF_EVEN);
    }
}
