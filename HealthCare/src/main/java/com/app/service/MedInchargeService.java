package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.MedInchargeRepository;
import com.app.dao.MedicineRepository;
import com.app.dto.LoginRequestDTO;
import com.app.dto.LoginResponseDTO;
import com.app.dto.MedQtyUpdateDTO;
import com.app.dto.MedicineDTO;
import com.app.dto.ProfileDTO;
import com.app.pojos.Medicine;
import com.app.pojos.MedicineIncharge;

@Service
@Transactional
public class MedInchargeService implements IMedInchargeService {

	@Autowired
	private MedInchargeRepository medInchargeRepo ; 
	
	@Autowired
	private MedicineRepository medRepo ; 
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public LoginResponseDTO login(LoginRequestDTO request) {
		MedicineIncharge medIncharge = medInchargeRepo.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		System.out.println(medIncharge);
		
		LoginResponseDTO resp = mapper.map(medIncharge, LoginResponseDTO.class);
		System.out.println(resp);
		return resp;
	}

	@Override
	public ProfileDTO showProfile(long id) {
		MedicineIncharge medIncharge = medInchargeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		System.out.println(medIncharge);
		ProfileDTO medInchargeDTO = mapper.map(medIncharge, ProfileDTO.class);
		System.out.println(medInchargeDTO);
		return medInchargeDTO;
	}

	@Override
	public String changePassword(long id, String oldPassword, String newPassword) {
		System.out.println("in service layer change password");
		MedicineIncharge medIncharge = medInchargeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		medIncharge.setPassword(newPassword);
		return "password changed successfully";
	}

	
	@Override
	public String addMedicine(MedicineDTO medicineDTO) {
		System.out.println("in service layer add medicine");
		Medicine med = mapper.map(medicineDTO, Medicine.class);
		medRepo.save(med);
		return "medicine added successfully";
	}
	
	@Override
	public String deleteMedicine(long id) {
		medRepo.deleteById(id);
		return "medicine deleted";
	}
	
	@Override
	public String updateQty(long id, MedQtyUpdateDTO medQtyDTO) {
		medRepo.udpateMedQty(id, medQtyDTO.getQuantity());
		return "qty updated successfully";
	}
	

}
