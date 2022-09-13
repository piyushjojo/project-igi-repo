package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MedInchargeChangePassword {
	
	private String oldPassword ; 
	private String newPassword ; 
	
}
