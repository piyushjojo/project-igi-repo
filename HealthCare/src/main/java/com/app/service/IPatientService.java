package com.app.service;

import com.app.dto.PatientLoginRequest;
import com.app.dto.PatientLoginResponse;
import com.app.dto.PatientProfileDTO;
import com.app.dto.PatientSignUpRequest;

public interface IPatientService {
	String signUp(PatientSignUpRequest request);
	
	long getPatientId(String email);
	
	PatientLoginResponse login(PatientLoginRequest request);
	PatientProfileDTO showProfile(long id);
	
	String changePassword(long id, String oldPassword, String newPassword);
	
	String deleteUser(long id);
	
	
	
	
}
