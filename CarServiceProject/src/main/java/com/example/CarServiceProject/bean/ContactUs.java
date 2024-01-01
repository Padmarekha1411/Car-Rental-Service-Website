package com.example.CarServiceProject.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ContactUs {

	@Id
	private String Name;
	private String Email;
	private String Subject;
	private String Message;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
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
}
