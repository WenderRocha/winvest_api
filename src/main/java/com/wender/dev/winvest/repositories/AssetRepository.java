package com.wender.dev.winvest.repositories;

import com.wender.dev.winvest.entities.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
