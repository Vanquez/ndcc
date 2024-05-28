package com.kindsonthegenius.fleetmsv2.officeequipments.controllers;

import com.kindsonthegenius.fleetmsv2.assetm.models.Asset;
import com.kindsonthegenius.fleetmsv2.officeequipments.models.OfficeEquipment;
import com.kindsonthegenius.fleetmsv2.officeequipments.services.OfficeEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OfficeEquipmentController {

     @Autowired
     private OfficeEquipmentService officeEquipmentService;


     public Model addModelAttributes(Model model){

         model.addAttribute("officeEquipments", officeEquipmentService.findAll());

         return model;
     }

    @GetMapping("/officeequipmenthome")
    public String officeEquipmentHome(){


        return "officeequipment/index";
    }
    @GetMapping("/officeequipments/equipments")
    public String officeEquipments(Model model){

         addModelAttributes(model);

        return "/officeequipment/equipments";
    }

    @GetMapping("/officeequipment/equipmentadd")
    public String equipmentAdd(){

        return "/officeequipment/equipmentAdd";
    }



    @RequestMapping("/officeequipment/equipment/{op}/{id}")
    public String findById(@PathVariable int id, @PathVariable String op, Model model){
       OfficeEquipment officeEquipment =  officeEquipmentService.findById(id);
       model.addAttribute("officeEquipment", officeEquipment);
         return "/officeequipment/equipment" + op;
    }



    @PostMapping("/officeequipments/equipments")
    public String addNew(OfficeEquipment officeEquipment){

        officeEquipmentService.save(officeEquipment);

        return "redirect:/officeequipments/equipments";
    }


    // Method to delete Equipments
    @RequestMapping(value = "/officeequipment/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@PathVariable int id){

         officeEquipmentService.deleById(id);

         return "redirect:/officeequipments/equipments";
    }

}
