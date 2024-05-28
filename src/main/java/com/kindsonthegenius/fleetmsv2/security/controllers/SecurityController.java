package com.kindsonthegenius.fleetmsv2.security.controllers;

import com.kindsonthegenius.fleetmsv2.es.services.EmployeeService;
import com.kindsonthegenius.fleetmsv2.security.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private EmployeeService employeeService;

   public  Model addModelAttributes(Model model){
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("countEmployee", employeeService.getNumberEmployee());

        return model;
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "security/login";
    }

    @GetMapping("/register")
    public String register() {
        return "security/register";
    }

    @RequestMapping("/index")
    public String homePage(Model model) {

          addModelAttributes(model);
        return "index";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}
