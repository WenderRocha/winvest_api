package com.wender.dev.winvest.entities;

import com.wender.dev.winvest.entities.enums.OperationResult;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @NotNull(message = "O campo stop gain é requerido.")
    @NonNull
    private BigDecimal target;

    @NotNull(message = "O campo stop loss é requerido.")
    @NonNull
    private BigDecimal stop;


    @NotNull(message = "O campo carteira é requerido.")
    @NonNull
    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)

    @Transient
    private OperationResult operationResult;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "management", fetch = FetchType.LAZY)
    private Set<Operation> operations = new HashSet<>();

    public BigDecimal getBalance() {
        return wallet.getBalance()
                .add(getTotalProfit())
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getTotalProfit() {
        BigDecimal sum = BigDecimal.ZERO;

        for (Operation x : operations) {

            if (x.getOperationResult() == operationResult.LOSS) {
                sum = sum.subtract(x.getOperationLoss());
            } else if (x.getOperationResult() == operationResult.WIN) {
                sum = sum.add(x.getOperationProfit());
            }
        }

        return sum.setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getProfitTarget() {
        BigDecimal percentual = this.target.divide(new BigDecimal("100"));
        return percentual
                .multiply(getBalance())
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getProfitPercentage() {
        return getBalance()
                .divide(wallet.getBalance(), 3, RoundingMode.HALF_EVEN)
                .subtract(new BigDecimal("1.0"))
                .multiply(new BigDecimal("100"));

    }

    public BigDecimal getWinToday(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        BigDecimal sum = BigDecimal.ZERO;
        LocalDateTime now = LocalDateTime.now();

        for (Operation x : operations) {
            if( x.getOperationResult() == OperationResult.WIN && x.getDate().format(formatter).equals(now.format(formatter))){
                sum = sum.add(x.getOperationProfit());
            }
        }
        return sum.setScale(2, RoundingMode.HALF_EVEN);

    }

    public BigDecimal getLossToday(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        BigDecimal sum = BigDecimal.ZERO;
        LocalDateTime now = LocalDateTime.now();

        for (Operation x : operations) {
            if( x.getOperationResult() == OperationResult.LOSS && x.getDate().format(formatter).equals(now.format(formatter))){
                sum = sum.add(x.getOperationProfit());
            }
        }
        return sum.setScale(2, RoundingMode.HALF_EVEN);

    }

    public BigDecimal getProfitToday() {
       return getWinToday().subtract(getLossToday()).setScale(2, RoundingMode.HALF_EVEN);
    }
}
