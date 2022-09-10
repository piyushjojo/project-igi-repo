package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PatientLoginResponse {
	private String name;
	private String email;
	
	public PatientLoginResponse(String name, String email) {
		super();
		this.name = name; 
		this.email = email;
	}

}
