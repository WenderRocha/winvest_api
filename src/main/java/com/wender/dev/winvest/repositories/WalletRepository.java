package com.wender.dev.winvest.repositories;

import com.wender.dev.winvest.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
