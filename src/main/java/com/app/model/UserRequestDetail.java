package com.app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.*;


public class UserRequestDetail {
    
	@NotNull(message = "first Name cannot be null")
	String fname;
	
	@NotNull(message = "email CANNOT be null")
	@Size(min = 8, max = 19,message = "email of invalid size")
	String email;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
