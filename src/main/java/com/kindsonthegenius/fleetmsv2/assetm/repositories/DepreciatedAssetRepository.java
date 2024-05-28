package com.kindsonthegenius.fleetmsv2.assetm.repositories;

import com.kindsonthegenius.fleetmsv2.assetm.models.DepreciatedAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepreciatedAssetRepository extends JpaRepository<DepreciatedAsset, Long> {
}