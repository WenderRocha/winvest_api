package com.wender.dev.winvest.repositories;

import com.wender.dev.winvest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
