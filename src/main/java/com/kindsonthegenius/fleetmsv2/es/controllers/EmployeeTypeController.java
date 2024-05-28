package com.kindsonthegenius.fleetmsv2.es.controllers;

import com.kindsonthegenius.fleetmsv2.es.models.EmployeeType;
import com.kindsonthegenius.fleetmsv2.es.repositories.EmployeeTypeRepository;
import com.kindsonthegenius.fleetmsv2.es.services.EmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeTypeController {

	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;

	@Autowired
	private EmployeeTypeService employeeTypeService;

	@GetMapping("/es/clusteradvisoryGroups")
	public String parameters(Model model){
		List<EmployeeType> employeeTypes = employeeTypeRepository.findAll();
		model.addAttribute("employeeTypes", employeeTypes);
		return "es/clusteradvisoryGroups";
	}

	//Get Job Title by id
	@GetMapping("/hr/employeeType/{id}")
	@ResponseBody
	public EmployeeType getById(@PathVariable Integer id){
		return employeeTypeService.findById(id).orElse(null);
	}

	@PostMapping("/hr/employeeTypes")
	public String save(EmployeeType employeeType){
		employeeTypeService.save(employeeType);
		return "redirect:/hr/employeeTypes";
	}

	@RequestMapping(value="/hr/employeeType/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		employeeTypeService.delete(id);
		return "redirect:/hr/employeeTypes";
	}

}
