package com.wender.dev.winvest.repositories;

import com.wender.dev.winvest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT obj FROM User obj WHERE obj.cpf =:cpf")
    User findByCPF(@Param("cpf")String cpf);

    @Query("SELECT obj FROM User obj WHERE obj.email =:email")
    User findByEMAIL(@Param("email")String email);

    @Query("SELECT obj FROM User obj WHERE obj.phone =:phone")
    User findByPhone(@Param("phone")String phone);

    public Optional<User> findByEmail(String email);
}
