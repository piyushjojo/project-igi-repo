package com.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.PatientRepository;
import com.app.dto.ApiResponse;
import com.app.dto.PatientChangePassword;
import com.app.dto.PatientEmailDTO;
import com.app.dto.PatientLoginRequest;
import com.app.dto.PatientLoginResponse;
import com.app.dto.PatientProfileDTO;
import com.app.dto.PatientSignUpRequest;
import com.app.pojos.Patient;
import com.app.service.IPatientService;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

	@Autowired
	private IPatientService patientService;
	
	@Autowired
	private PatientRepository patientRepo ; 

	public PatientController() {
		System.out.println("in patient controller " + getClass());
	}
	
	//user signup 
	@PostMapping("/signup1")
	public ResponseEntity<?> signUp(@RequestBody @Valid PatientEmailDTO patientEmailDTO){
		System.out.println("in signup email confirmation page");
		Patient patient = patientRepo.findByEmail(patientEmailDTO.getEmail());
		if(patient == null || !patient.isStatus()) {
			return new ResponseEntity<>(new ApiResponse("new user"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ApiResponse("user already exist"), HttpStatus.BAD_REQUEST);
	}
	//otp feature add here

	//how to get email from signup1 to signup2 
	@PostMapping("/signup2")
	public ResponseEntity<?> signUp(@RequestBody @Valid PatientSignUpRequest request){
		System.out.println("in user signup ");
		
		try {
			return ResponseEntity.ok(new ApiResponse(patientService.signUp(request)));
		} catch (RuntimeException e) {
			System.out.println("err in add emp " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Credentials"), HttpStatus.BAD_REQUEST);
		}
	}

	// add REST end point for user login
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid PatientLoginRequest request,
			HttpSession httpSession) {
		System.out.println("in user login " + request);
//		long id = patientService.getPatientId(request.getEmail());
		try {
			PatientLoginResponse response = patientService.login(request);
			httpSession.setAttribute("patient_login_response", response);
			return ResponseEntity.ok(response);
		} catch (RuntimeException e) {
			System.out.println("err in add emp " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Credentials"), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/profile")
	public PatientProfileDTO patientProfile(HttpSession httpSession) {
		System.out.println("in patient profile ");
		PatientLoginResponse patient = (PatientLoginResponse) httpSession.getAttribute("patient_login_response");
		System.out.println(patientService.showProfile(patient.getId()));
		return patientService.showProfile(patient.getId());
	}
	
	@PutMapping("/profile/changePassword/{id}")
	public ResponseEntity<?> changePassword(@RequestBody PatientChangePassword pcp ,@PathVariable long id){
		System.out.println("in  controller change password");
		try {
			patientService.changePassword(id , pcp.getOldPassword(), pcp.getNewPassword());
			return new ResponseEntity<>(new ApiResponse("pasword changed successfully"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ApiResponse("Invalid old password"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/profile/delete/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable long id){
		System.out.println("in delete user ");
		patientService.deleteUser(id);
		return new ResponseEntity<>(new ApiResponse("user deleted successfully"), HttpStatus.OK);
	}

	@PostMapping("/signout")
	public ResponseEntity<?> signout(HttpSession httpSession) {
		PatientLoginResponse patient = (PatientLoginResponse) httpSession.getAttribute("patient_login_response");
		httpSession.invalidate();
		return new ResponseEntity<>(new ApiResponse(patient.getName() +" Logged out Successfully"), HttpStatus.OK);
	}

}
