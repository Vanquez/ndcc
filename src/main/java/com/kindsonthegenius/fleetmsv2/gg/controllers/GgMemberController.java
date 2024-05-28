package com.kindsonthegenius.fleetmsv2.gg.controllers;

import com.kindsonthegenius.fleetmsv2.es.services.EmployeeService;
import com.kindsonthegenius.fleetmsv2.es.services.EmployeeTypeService;
import com.kindsonthegenius.fleetmsv2.es.services.JobTitleService;
import com.kindsonthegenius.fleetmsv2.gg.models.GgMember;
import com.kindsonthegenius.fleetmsv2.gg.services.GgMemberService;
import com.kindsonthegenius.fleetmsv2.parameters.services.CountryService;
import com.kindsonthegenius.fleetmsv2.parameters.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GgMemberController {
    @Autowired
    private GgMemberService ggMemberService;
    @Autowired private EmployeeService employeeService;
    @Autowired private StateService stateService;
    @Autowired private JobTitleService jobTitleService;
    @Autowired private EmployeeTypeService employeeTypeService;
    @Autowired private CountryService countryService;

    public Model addModelAttributes(Model model){
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("states", stateService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("jobTitles", jobTitleService.findAll());
        model.addAttribute("employeeTypes", employeeTypeService.findAll());
        model.addAttribute("ggMembers", ggMemberService.findAll());
        return model;
    }

    //Method to return good governance members
    @GetMapping("/gg/ggmembers")
    public String ggMembers(Model model){

         addModelAttributes(model);

        return "/gg/ggmembers";
    }

    // Method to return Member Add
    @GetMapping("/gg/ggmemberadd")
    public String ggMemberAdd(Model model){
      addModelAttributes(model);
        return "/gg/ggmemberadd";
    }

    // Method to save a Member

    @PostMapping("/gg/ggmembers")
    public String saveGgMember(GgMember ggMember){

        ggMemberService.save(ggMember);
        return "redirect:/gg/ggmembers";
    }
}
