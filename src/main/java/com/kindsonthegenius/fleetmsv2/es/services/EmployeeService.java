package com.kindsonthegenius.fleetmsv2.es.services;

import com.kindsonthegenius.fleetmsv2.es.models.Employee;
import com.kindsonthegenius.fleetmsv2.es.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
		
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//Get All Employees
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}	
	
	//Get Employee By Id
	public Employee findById(int id) {
		return employeeRepository.findById(id).orElse(null);
	}	
	
	//Delete Employee
	public void delete(int id) {
		employeeRepository.deleteById(id);
	}
	
	//Update Employee
	public void save( Employee employee) {
		employeeRepository.save(employee);
	}
	
	//Get Employee by username
	public Employee findByUsername(String un) {
		return employeeRepository.findByUsername(un);
	}

	//Get employee by Keyword
	public List<Employee> findByKeyword(String keyword) {
		return employeeRepository.findByKeyword(keyword);
	}

   // Get total number of employees
	public List<Integer> getNumberEmployee(){return  employeeRepository.getNumberEmployee();}

   // Method to return Environment & Sustainability Members
   public List<Employee> esMembers(){
		return employeeRepository.findByColumnNameESCluster();
   }
}
