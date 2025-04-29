package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.model.Employee;

@Component
public class DaoRepository { // manual inserting and getting details of MySql

//public interface DaoRepository {
//	List<Employee> getAllEmployees();    //  getAllEmployees() is a method that returns a List of Employee objects,
//										// where each Employee object contains details such as the employee's name, id ..
//
//	Employee insertEmployee(Employee emp);
//}
	
	
	@Autowired 		// auto dependences injection
	private JdbcTemplate jdbcTemplate;

	private static final String GET_EMPL = "select first_name, last_name, email_id from employees";
	private static final String INSERT_EMPL = " insert into employees(first_name, last_name, email_id) values(?,?,?)";
	private static final String UPDATE_EMPL = "UPDATE employees SET first_name = ?, last_name = ?, email_id = ? WHERE id = ?";

	
	public List<Employee> getAllEmployees() { // based on return type, parameters will be passed
			
			List<Employee> emp = null;

			emp = jdbcTemplate.query(GET_EMPL, (rs, rowNum) -> {
				
				Employee e = new Employee();
				
				e.setId(rs.getLong("id"));
				e.setFirstName(rs.getString("first_name"));
				e.setEmailId(rs.getString("email_address"));
				e.setLastName(rs.getString("last_name"));
				return e; 
			});
		
			System.out.println("Employee data : " + emp);
			return emp;
	}  
	
	public String insertEmployee(Employee emp) {
		int result = jdbcTemplate.update(INSERT_EMPL, 
				emp.getFirstName(), 
				emp.getLastName(), 
				emp.getEmailId()
				);
		return result > 0 ? "Employee inserted successfully": "Failed to insert employee";
		
	}
	
	public int updateEmployee(Employee emp) {
	    
	    return jdbcTemplate.update(UPDATE_EMPL,
	        emp.getFirstName(),
	        emp.getLastName(),
	        emp.getEmailId(),
	        emp.getId()
	    );
	}
}