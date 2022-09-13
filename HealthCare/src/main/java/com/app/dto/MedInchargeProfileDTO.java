package com.app.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.pojos.Gender;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MedInchargeProfileDTO {
	
	private String email;
	
	private String name;
	private long phone;
	
	private String address ; 
	
	private LocalDate dob;
	
	private Gender gender;
	
}
