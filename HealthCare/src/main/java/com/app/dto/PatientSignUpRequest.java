package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.app.pojos.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientSignUpRequest {
	// To DO : add validation rules
	@NotBlank(message = "name is required")
	private String name;

	@NotBlank(message = "email is required")
	@Email(message = "invalid email format")
	private String email;

//	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid password")
	private String password;


//	@NotBlank(message = "mobile no is required")
	@JsonProperty(value = "phone_no")
	private long phone;

	@NotBlank(message = "address is required")
	private String address;

	@PastOrPresent(message = "dob must be provided")
	private LocalDate dob;

	private Gender gender;

}
