package com.kindsonthegenius.fleetmsv2.officeequipments.repositories;

import com.kindsonthegenius.fleetmsv2.officeequipments.models.OfficeEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeEquipmentRepository extends JpaRepository<OfficeEquipment,Integer> {

}
