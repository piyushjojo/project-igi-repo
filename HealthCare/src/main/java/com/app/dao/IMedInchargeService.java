package com.app.dao;

import com.app.dto.LoginRequestDTO;
import com.app.dto.LoginResponseDTO;
import com.app.dto.MedQtyUpdateDTO;
import com.app.dto.MedicineDTO;
import com.app.dto.ProfileDTO;

public interface IMedInchargeService {
	
//	long getPatientId(String email);
	
	LoginResponseDTO login(LoginRequestDTO request);
	ProfileDTO showProfile(long id);
	
	String changePassword(long id, String oldPassword, String newPassword);
	
	String addMedicine(MedicineDTO medicineDTO);
	
	String deleteMedicine(long id);
	String updateQty(long id, MedQtyUpdateDTO medQtyDTO);
	
//	String deleteUser(long id);
	
	
	
	
}
