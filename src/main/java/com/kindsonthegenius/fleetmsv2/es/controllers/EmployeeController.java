package com.kindsonthegenius.fleetmsv2.es.controllers;

import com.kindsonthegenius.fleetmsv2.es.models.Employee;
import com.kindsonthegenius.fleetmsv2.es.services.EmployeeService;
import com.kindsonthegenius.fleetmsv2.es.services.EmployeeTypeService;
import com.kindsonthegenius.fleetmsv2.es.services.JobTitleService;
import com.kindsonthegenius.fleetmsv2.parameters.services.CountryService;
import com.kindsonthegenius.fleetmsv2.parameters.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class EmployeeController {
	
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
		return model;
	}

	//Get All Employees
	@GetMapping("/es/esmembers")
	public String findAll(Model model){
		addModelAttributes(model);
		List<Employee> test = employeeService.esMembers();
		System.out.println(test);
		return "/es/esmembers";
	}	

	@GetMapping("/es/esmemberadd")
	public String addEmployee(Model model){
		addModelAttributes(model);
		return "/es/esmemberadd";
	}

	//The op parameter is either Edit or Details
	@GetMapping("/es/memeber/{op}/{id}")
	public String editEmployee(@PathVariable Integer id, @PathVariable String op, Model model){
		Employee employee = employeeService.findById(id);
		model.addAttribute("employee", employee);
		addModelAttributes(model);
		return "/es/member"+ op; //returns employeeEdit or employeeDetails
	}

	//Add Employee
	@PostMapping("/es/esmembers")
	public String addNew(Employee employee) {
		employeeService.save(employee);
		return "redirect:/es/esmembers";
	}	

	@RequestMapping(value="/es/esmember/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		employeeService.delete(id);
		return "redirect:/es/esmembers";
	}	

	@RequestMapping(value="/employees/uploadPhoto", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		File newFile = new File("D:\\SOLUTIONS\\fleetms\\uploads" + file.getOriginalFilename());
		newFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(newFile);
		fout.write(file.getBytes());
		fout.close();
		return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
	}	

	@PostMapping("/employees/uploadPhoto2")
	public String uploadFile2(@RequestParam("file") MultipartFile file, Principal principal) 
			throws IllegalStateException, IOException {
			String baseDirectory = "D:\\SOLUTIONS\\fleetms\\src\\main\\resources\\static\\img\\photos\\" ;
			file.transferTo(new File(baseDirectory + principal.getName() + ".jpg"));
			return "redirect:/employees";
	}

	@RequestMapping(value="/employee/profile")
	public String profile(Model model, Principal principal) {
		String un = principal.getName();
		addModelAttributes(model);
		model.addAttribute("employee", employeeService.findByUsername(un));
		return "profile";
	}

//	@GetMapping("/hr/assignemployee/{op}/{id}")
//	public String editAssign(@PathVariable int id, @PathVariable String op, Model model){
//		Employee employees = employeeService.findById(id);
//		model.addAttribute("employee", employees);
//		model.addAttribute("assigned", employeeService.getAssign());
//		model.addAttribute("assigned", employeeService.getNotAssigned());
//
//		return "";
//	}
}
