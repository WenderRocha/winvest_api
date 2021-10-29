package com.wender.dev.winvest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "management", fetch = FetchType.LAZY)
    private Set<Operation> operations = new HashSet<>();

    public BigDecimal getBalance(){
        return wallet.getBalance().add(getTotalProfit());
    }
    public BigDecimal getTotalProfit() {
        BigDecimal sum = BigDecimal.ZERO;

        for (Operation x : operations) {
            sum = sum.add(x.getOperationProfit());
        }

        return sum;
    }

    public BigDecimal getProfitTarget() {
        BigDecimal percentual = this.target.divide(new BigDecimal("100"));
        return percentual.multiply(getBalance()).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getProfitPercentage(){
      return getBalance().divide(wallet.getBalance()).subtract(new BigDecimal("1")).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_EVEN);
    }
}
