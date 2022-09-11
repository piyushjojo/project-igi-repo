package com.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.PatientLoginRequest;
import com.app.service.IPatientService;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

	@Autowired
	private IPatientService patientService ; 
	public PatientController() {
		System.out.println("in patient controller " + getClass());
	}

	// add REST end point for user login
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid PatientLoginRequest request , HttpSession httpSession) {
		System.out.println("in user login " + request);
		
		try {
			httpSession.setAttribute("patient_signin", request);
			return ResponseEntity.ok(patientService.login(request));
		}catch (RuntimeException e) {
			System.out.println("err in add emp " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Credentials"), HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@PostMapping("/signout")
	public ResponseEntity<?> signout(HttpSession httpSession){
		PatientLoginRequest patientLogout =(PatientLoginRequest) httpSession.getAttribute("patient_signin");
		httpSession.invalidate();
		return new ResponseEntity<>(new ApiResponse(" Logout Successful"), HttpStatus.OK);
		
	}
}
