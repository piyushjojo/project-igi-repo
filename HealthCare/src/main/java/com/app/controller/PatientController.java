package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.PatientLoginRequest;
import com.app.service.IPatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private IPatientService patientService ; 
	public PatientController() {
		System.out.println("in patient controller " + getClass());
	}

	// add REST end point for user login
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid PatientLoginRequest request) {
		System.out.println("in user login " + request);
		
		try {
			return ResponseEntity.ok(patientService.login(request));
		}catch (RuntimeException e) {
			System.out.println("err in add emp " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Credentials"), HttpStatus.BAD_REQUEST);// => invalid data from
																									// clnt
		}
	}
}
