package com.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class LoginRequestDTO {
	@NotBlank(message = "email is required")
	private String email;
	@NotBlank(message = "password is required")
	private String password;
	
}
