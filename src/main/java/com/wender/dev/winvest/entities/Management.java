package com.wender.dev.winvest.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
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

    @NonNull
    private BigDecimal target;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "management")
    private Set<Operation> operations = new HashSet<>();

    public BigDecimal totalProfit() {

        BigDecimal sum = BigDecimal.ZERO;

        for (Operation x :operations) {
            sum = wallet.getBalance().add(x.getTake());
        }

        return sum;
    }

    public BigDecimal profitTake() {
        return this.target.divide(new BigDecimal("100")).setScale(2, RoundingMode.HALF_EVEN);
    }
}
