package com.example.CarServiceProject.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Get_Email {

	@Id
	private String Customer;
	private String Name;
	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	private String Subject;
	private String Message;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCustomer() {
		return Customer;
	}

	public void setCustomer(String customer) {
		Customer = customer;
	}

	
}
