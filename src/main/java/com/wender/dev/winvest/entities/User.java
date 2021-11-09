package com.wender.dev.winvest.entities;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_user")
public class User implements Serializable {


    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotEmpty(message = "O campo nome é requerido")
    private String name;

    @Column(unique = true)
    @NonNull
    @NotEmpty(message = "O campo email é requerido")
    private String email;

    @Column(unique = true)
    @NonNull
    @NotEmpty(message = "O campo telefone é requerido.")
    private String phone;

    @EqualsAndHashCode.Include
    @CPF
    @NonNull
    @NotEmpty(message = "O campo CPF é requerido.")
    @Column(unique = true)
    private String cpf;

    @NonNull
    @NotEmpty(message = "O campo senha é requerido.")
    private String password;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Wallet> wallets = new ArrayList<>();
}
