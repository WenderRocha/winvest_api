package com.wender.dev.winvest.dtos;

import com.wender.dev.winvest.entities.Management;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ManagementDTO implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "O campo stop gain é requerido.")
    private BigDecimal target;

    @NotNull(message = "O campo stop loss é requerido.")
    private BigDecimal stop;

    @NotNull(message = "O campo carteira é requerido.")
    private Long wallet;

    public ManagementDTO(Management obj) {
        this.id = obj.getId();
        this.target = obj.getTarget();
        this.stop = obj.getStop();
        this.wallet = obj.getWallet().getId();
    }
}
