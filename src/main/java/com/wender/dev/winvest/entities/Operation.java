package com.wender.dev.winvest.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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


    public Operation(Long id, LocalDateTime date, String assets, String comments, BigDecimal payout,
                     BigDecimal value, BigDecimal take, ResultOperation resultOperation) {
        this.id = id;
        this.date = date;
        this.assets = assets;
        this.comments = comments;
        this.payout = payout;
        this.value = value;
        this.take = take;
        this.resultOperation = (resultOperation == null) ? 0 : resultOperation.getCode();
    }

    public ResultOperation getResultOperation() {
        return ResultOperation.toEnum(this.resultOperation);
    }

    public void setResultOperation(ResultOperation resultOperation) {
        this.resultOperation = resultOperation.getCode();
    }

    public BigDecimal operationProfit() {
        return value.add(payout.divide(new BigDecimal("100")).setScale(2, RoundingMode.HALF_EVEN));
    }
}
