package com.wender.dev.winvest.dtos;
import com.wender.dev.winvest.entities.Wallet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class WalletDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "O campo nome é requerido.")
    private String name;

    @NotNull(message = "O campo saldo é requerido.")
    private BigDecimal balance;

    private String imgUrl;

    @NotNull(message = "O campo usuário é requerido.")
    private Long user;

    public WalletDTO(Wallet obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.balance = obj.getBalance();
        this.imgUrl = obj.getImgUrl();
        this.user =  obj.getUser().getId();
    }
}
