package com.app.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String token;
	private long id ; 
	private String name;
	private String email;
	
	public LoginResponseDTO(String token , long id ,String name, String email) {
		super();
		this.token = token;
		this.id = id;
		this.name = name; 
		this.email = email;
	}
	
	public LoginResponseDTO(String token , long id) {
		this.token = token;
		this.id = id;
	}

}
