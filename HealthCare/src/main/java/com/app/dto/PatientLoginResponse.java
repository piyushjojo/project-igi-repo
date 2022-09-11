package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class PatientLoginResponse {
	private String name;
	private String email;
	
	public PatientLoginResponse() {
		// TODO Auto-generated constructor stub
	}
	public PatientLoginResponse(String name, String email) {
		this.name = name; 
		this.email = email;
	}

}
