package com.wender.dev.winvest.repositories;

import com.wender.dev.winvest.entities.Management;
import com.wender.dev.winvest.entities.User;
import com.wender.dev.winvest.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ManagementRepository extends JpaRepository<Management, Long> {

}
