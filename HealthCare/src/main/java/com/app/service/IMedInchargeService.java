package com.app.service;

import com.app.dto.MedInchargeLoginRequest;
import com.app.dto.MedInchargeLoginResponse;
import com.app.dto.MedInchargeProfileDTO;
import com.app.dto.MedQtyUpdateDTO;
import com.app.dto.MedicineDTO;

public interface IMedInchargeService {
	
//	long getPatientId(String email);
	
	MedInchargeLoginResponse login(MedInchargeLoginRequest request);
	MedInchargeProfileDTO showProfile(long id);
	
	String changePassword(long id, String oldPassword, String newPassword);
	
	String addMedicine(MedicineDTO medicineDTO);
	
	String deleteMedicine(long id);
	String updateQty(long id, MedQtyUpdateDTO medQtyDTO);
	
//	String deleteUser(long id);
	
	
	
	
}
