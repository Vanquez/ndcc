package com.kindsonthegenius.fleetmsv2.assetm.repositories;

import com.kindsonthegenius.fleetmsv2.assetm.models.Asset;
import com.kindsonthegenius.fleetmsv2.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {

    @Query("SELECT a FROM Asset a WHERE a.createdDate <= :cutoffDate")
    List<Asset> findOldAssets(@Param("cutoffDate") Date cutoffDate);

    @Transactional
    @Modifying
    @Query("DELETE FROM Asset a WHERE a.createdDate <= :cutoffDate")
    void deleteOldAssets(@Param("cutoffDate") Date cutoffDate);




//    @Query(
//            value = "SELECT * FROM role WHERE id NOT IN (SELECT role_id FROM user_role WHERE user_id = ?1)",
//            nativeQuery = true
//    )
//    List<Role> getUserNotRoles(Integer userId);

    @Query(
            value = "SELECT * FROM asset WHERE asset_id NOT IN (SELECT asset_id FROM employee_asset WHERE id= ?1)",
            nativeQuery = true
    )

   List<Asset> getNotAssets(Integer employeeId);


}
