package com.wender.dev.winvest.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wender.dev.winvest.entities.enums.ResultOperation;
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
    private Integer resultOperation;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "management_id")
    private Management management;


    public Operation(Long id, LocalDateTime date, String assets, String comments, BigDecimal payout,
                     BigDecimal value, ResultOperation resultOperation, Management management) {
        this.id = id;
        this.date = date;
        this.assets = assets;
        this.comments = comments;
        this.payout = payout;
        this.value = value;
        this.resultOperation = (resultOperation == null) ? 0 : resultOperation.getCode();
        this.management = management;
    }

    public ResultOperation getResultOperation() {
        return ResultOperation.toEnum(this.resultOperation);
    }

    public void setResultOperation(ResultOperation resultOperation) {
        this.resultOperation = resultOperation.getCode();
    }

    public BigDecimal getOperationProfit() {
        BigDecimal percentual = this.payout.divide(new BigDecimal("100"));
        return this.value.add(percentual.multiply(this.value)).setScale(2, RoundingMode.HALF_EVEN);
    }
}
