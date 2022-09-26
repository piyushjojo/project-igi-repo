package com.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChangePasswordDTO {
	
	private String oldPassword ; 
	@NotBlank(message = "new password can not be blank" )
	private String newPassword ; 
	
}
