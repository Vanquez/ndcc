package com.kindsonthegenius.fleetmsv2.officeequipments.services;

import com.kindsonthegenius.fleetmsv2.officeequipments.models.OfficeEquipment;
import com.kindsonthegenius.fleetmsv2.officeequipments.repositories.OfficeEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Access;
import java.util.List;
import java.util.Optional;

@Service
public class OfficeEquipmentService {
    @Autowired
    private OfficeEquipmentRepository officeEquipmentRepository;


    //Method to save equipments
    public void save(OfficeEquipment officeEquipment){

        officeEquipmentRepository.save(officeEquipment);

    }

    //Method to retrieve saved equipments

    public List<OfficeEquipment> findAll(){

        return officeEquipmentRepository.findAll();
    }

    //Method to find by Id
   public OfficeEquipment findById(int id){

        return officeEquipmentRepository.findById(id).orElse(null);
   }

   public void deleById(int id){

        officeEquipmentRepository.deleteById(id);
   }

}
