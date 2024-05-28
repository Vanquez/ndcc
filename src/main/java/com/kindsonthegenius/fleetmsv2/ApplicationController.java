package com.kindsonthegenius.fleetmsv2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    //return document page
    @GetMapping("/uploadpage")
    public String goHome(){
        return "upload/upload";
    }
    // All memebers
    @GetMapping("es")
    public String hr(){
        return "/es/index";
    }

    // All memebers
    @GetMapping("cp")
    public String cp(){
        return "/parameters/cagparameters";
    }
    // Good Governance
    @GetMapping("/gg")
    public String gg(){
        return "/gg/index";
    }

    @GetMapping("fleet")
    public String fleet(){
        return "/fleet/index";
    }

    @GetMapping("asset")
    public String asset(){
        return "/assetm/index";
    }

    @GetMapping("accounts")
    public String accounts(){
        return "/accounts/index";
    }

    @GetMapping("payroll")
    public String payroll(){
        return "/payroll/index";
    }

    @GetMapping("helpdesk")
    public String helpdesk(){
        return "/helpdesk/index";
    }

    @GetMapping("parameters")
    public String parameters(){
        return "/parameters/index";
    }

    @GetMapping("reports")
    public String reports(){
        return "/reports/index";
    }

    @GetMapping("security")
    public String security(){
        return "/security/index";
    }

}
