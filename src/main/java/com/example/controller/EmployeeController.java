package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository; // class

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee details) { // Details
		System.out.println(" response passed******************** ");
		return employeeRepository.save(details);
	}

	@GetMapping("/allemployees")
	public List<Employee> getAllEmployees() {
		System.out.println("Request Recieved******************************");
		return employeeRepository.findAll();
	}

	@GetMapping("/getemployee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException { // ResourceNotFoundException --> create a class ResourceNotFoundException
												// and throws exception
		Employee details = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId)); // new
																														// ResourceNotFoundException()
		System.out.println(" getemployee sucessful************");
		return ResponseEntity.ok().body(details);

	}

	@PutMapping("/updateemployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee details = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		details.setEmailId(employeeDetails.getEmailId());
		details.setLastName(employeeDetails.getLastName());
		details.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeRepository.save(details);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/deleteemployees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        
        
        return ResponseEntity.ok(response) ;
    }
	
	
	
	

}