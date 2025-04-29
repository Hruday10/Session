package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // JPA represents a table in RDB scheme
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // This annotation is generally used in conjunction with the @Id annotation 
	private long Id;								// to automatically generate unique values for primary key columns within our database tables
	private String firstName;
	private String lastName;
	private String emailId;

	// effect in DaoRepository class
	public Employee(long id, String firstName, String secondName, String emailId) {
		
		Id = id;
		this.firstName = firstName;
		this.lastName = secondName;
		this.emailId = emailId;
	}

	public long getId() {
		return Id;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public void setId(long id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
