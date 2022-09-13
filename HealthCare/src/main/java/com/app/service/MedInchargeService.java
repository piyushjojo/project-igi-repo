package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.MedInchargeRepository;
import com.app.dao.MedicineRepository;
import com.app.dto.MedInchargeLoginRequest;
import com.app.dto.MedInchargeLoginResponse;
import com.app.dto.MedInchargeProfileDTO;
import com.app.dto.MedicineDTO;
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
	public MedInchargeLoginResponse login(MedInchargeLoginRequest request) {
		MedicineIncharge medIncharge = medInchargeRepo.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		System.out.println(medIncharge);
		
		MedInchargeLoginResponse resp = mapper.map(medIncharge, MedInchargeLoginResponse.class);
		System.out.println(resp);
		return resp;
	}

	@Override
	public MedInchargeProfileDTO showProfile(long id) {
		MedicineIncharge medIncharge = medInchargeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		System.out.println(medIncharge);
		MedInchargeProfileDTO medInchargeDTO = mapper.map(medIncharge, MedInchargeProfileDTO.class);
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
	
	

}
