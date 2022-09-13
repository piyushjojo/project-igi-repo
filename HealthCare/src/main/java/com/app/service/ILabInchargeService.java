package com.app.service;

import com.app.dto.LabTestsDTO;
import com.app.dto.LoginRequestDTO;
import com.app.dto.LoginResponseDTO;
import com.app.dto.ProfileDTO;

public interface ILabInchargeService {
	
//	long getPatientId(String email);
	
	LoginResponseDTO login(LoginRequestDTO request);
	ProfileDTO showProfile(long id);
	
	String changePassword(long id, String oldPassword, String newPassword);
	
	String addLabTest(LabTestsDTO labTestDTO);
	
	String deleteLabTest(long id);
//	String updateQty(long id, MedQtyUpdateDTO medQtyDTO);
	
//	String deleteUser(long id);
	
}
