package com.wender.dev.winvest.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wender.dev.winvest.entities.Operation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OperationDTO implements Serializable {

    @EqualsAndHashCode.Include
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

    private BigDecimal take;

    @NotNull(message = "O campo resultado é requerido.")
    private Integer operationResult;

    @NotNull(message = "O campo carteira é requerido.")
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
