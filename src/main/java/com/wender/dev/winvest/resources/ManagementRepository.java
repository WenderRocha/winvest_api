package com.wender.dev.winvest.resources;

import com.wender.dev.winvest.entities.Management;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagementRepository extends JpaRepository<Management, Long> {
}
