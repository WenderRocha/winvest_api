package com.wender.dev.winvest.dtos;

import com.wender.dev.winvest.entities.User;
import com.wender.dev.winvest.entities.Wallet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {

    private Long id;
    private String name;
    private String phone;
    private String email;

    public UserDTO(User obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.phone = obj.getPhone();
        this.email = obj.getEmail();
    }
}
