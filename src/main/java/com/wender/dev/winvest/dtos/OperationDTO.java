package com.wender.dev.winvest.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wender.dev.winvest.entities.Operation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class OperationDTO implements Serializable {

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;
    private String assets;
    private String comments;
    private BigDecimal payout;
    private BigDecimal value;
    private BigDecimal take;
    private Integer operationResult;
    private Long management;

    public OperationDTO(Operation obj) {
        this.id = obj.getId();
        this.date = obj.getDate();
        this.assets = obj.getAssets();
        this.comments = obj.getComments();
        this.payout = obj.getPayout();
        this.value = obj.getValue();
        this.take = obj.getTake();
        this.operationResult = obj.getOperationResult().getCode();
        this.management = obj.getManagement().getId();
    }
}
