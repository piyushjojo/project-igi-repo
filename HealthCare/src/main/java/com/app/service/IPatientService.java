package com.app.service;

import com.app.dto.LoginRequestDTO;
import com.app.dto.LoginResponseDTO;
import com.app.dto.PatientSignUpRequest;
import com.app.dto.ProfileDTO;
import com.app.pojos.Patient;

public interface IPatientService {
	String signUp(PatientSignUpRequest request);
	
	long getPatientId(String email);
	
	LoginResponseDTO login(LoginRequestDTO request);
	ProfileDTO showProfile(long id);
	
	String changePassword(long id, String oldPassword, String newPassword);
	
	String deleteUser(long id);

	Patient findByEmail(String email);

	String walletRecharge(long id, double amount);

	Patient getWallet(long id);

	

	
	
}
