package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MedInchargeLoginResponse {
	private long id ; 
	private String name;
	private String email;
	
	public MedInchargeLoginResponse(long id ,String name, String email) {
		super();
		this.id = id;
		this.name = name; 
		this.email = email;
	}

}
