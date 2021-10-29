package com.wender.dev.winvest.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wender.dev.winvest.entities.enums.OperationResult;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_operation")
public class Operation implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;

    private String assets;
    private String comments;
    private BigDecimal payout;
    private BigDecimal value;
    private BigDecimal take;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Integer operationResult;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "management_id")
    private Management management;

    public Operation(Long id, LocalDateTime date, String assets, String comments, BigDecimal payout,
                     BigDecimal value, OperationResult operationResult, Management management) {
        this.id = id;
        this.date = date;
        this.assets = assets;
        this.comments = comments;
        this.payout = payout;
        this.value = value;
        this.take = (operationResult.getCode() == 1) ? operationProfit() : BigDecimal.ZERO;
        this.operationResult = (operationResult == null) ? 0 : operationResult.getCode();
        this.management = management;
    }

    public OperationResult getOperationResult() {
        return OperationResult.toEnum(this.operationResult);
    }

    public void setOperationResult(OperationResult operationResult) {
        this.operationResult = operationResult.getCode();
    }

    public BigDecimal operationProfit() {
        BigDecimal percentual = this.payout.divide(new BigDecimal("100"));
        return percentual
                .multiply(this.value)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal operationLoss() {
        return this.value;
    }

    public BigDecimal getResult() {
        if (getOperationResult() == OperationResult.WIN) {
            return operationProfit();
        } else if (getOperationResult() == OperationResult.LOSS) {
            return operationLoss();
        }

        return BigDecimal.ZERO;
    }
}
