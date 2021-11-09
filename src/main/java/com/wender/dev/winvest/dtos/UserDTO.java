package com.wender.dev.winvest.dtos;

import com.wender.dev.winvest.entities.User;
import com.wender.dev.winvest.entities.Wallet;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserDTO implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "O campo nome é requerido.")
    private String name;

    @NotNull(message = "O campo email é requerido.")
    private String email;

    @NotNull(message = "O campo telefone é requerido.")
    private String phone;

    @NotNull(message = "O campo cpf é requerido.")
    @Column(unique = true)
    private String cpf;

    @NotNull(message = "O campo senha é requerido.")
    private String password;

    public UserDTO(User obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.phone = obj.getPhone();
        this.email = obj.getEmail();
    }
}
