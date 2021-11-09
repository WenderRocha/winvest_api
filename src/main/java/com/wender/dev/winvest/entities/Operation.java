package com.wender.dev.winvest.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wender.dev.winvest.entities.enums.OperationResult;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "O campo ativo é requerido.")
    private String assets;

    @NotNull(message = "O campo observação é requerido.")
    private String comments;

    @NotNull(message = "O campo payout é requerido.")
    private BigDecimal payout;

    @NotNull(message = "O campo valor é requerido.")
    private BigDecimal value;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private BigDecimal take;


    @NotNull(message = "O campo resultado é requerido.")
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Integer operationResult;

    @NotNull(message = "O campo carteira é requerido.")
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
        this.operationResult = (operationResult == null) ? 0 : operationResult.getCode();
        this.management = management;
    }

    public OperationResult getOperationResult() {
        return OperationResult.toEnum(this.operationResult);
    }

    public void setOperationResult(OperationResult operationResult) {
        this.operationResult = operationResult.getCode();
    }

    public BigDecimal getOperationProfit() {
        BigDecimal percentual = this.payout.divide(new BigDecimal("100"));
        return percentual
                .multiply(this.value)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getOperationLoss() {
        return this.value;
    }

    public BigDecimal getResult() {
        if (getOperationResult() == OperationResult.WIN) {
            return getOperationProfit();
        } else if (getOperationResult() == OperationResult.LOSS) {
            return getOperationLoss();
        }

        return BigDecimal.ZERO;
    }

    public BigDecimal getTake() {
        return this.take;
    }

    public BigDecimal setTake(OperationResult operationResult) {
        return this.take = (operationResult.getCode() == 1) ? getOperationProfit() : BigDecimal.ZERO;
    }
}
