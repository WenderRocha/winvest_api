package com.wender.dev.winvest.repositories;

import com.wender.dev.winvest.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
