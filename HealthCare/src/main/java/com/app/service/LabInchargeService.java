package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.LabInchargeRepository;
import com.app.dao.LabTestRepository;
import com.app.dto.LabTestsDTO;
import com.app.dto.LoginRequestDTO;
import com.app.dto.LoginResponseDTO;
import com.app.dto.MedQtyUpdateDTO;
import com.app.dto.ProfileDTO;
import com.app.pojos.LabIncharge;
import com.app.pojos.LabTests;

@Service
@Transactional
public class LabInchargeService implements ILabInchargeService {

	@Autowired
	private LabInchargeRepository labInchargeRepo ; 
	
	@Autowired
	private LabTestRepository labTestRepo ; 
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public LoginResponseDTO login(LoginRequestDTO request) {
		LabIncharge labIncharge = labInchargeRepo.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		System.out.println(labIncharge);
		
		LoginResponseDTO resp = mapper.map(labIncharge, LoginResponseDTO.class);
		System.out.println(resp);
		return resp;
	}

	@Override
	public ProfileDTO showProfile(long id) {
		LabIncharge labIncharge = labInchargeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		System.out.println(labIncharge);
		ProfileDTO medInchargeDTO = mapper.map(labIncharge, ProfileDTO.class);
		System.out.println(medInchargeDTO);
		return medInchargeDTO;
	}

	@Override
	public String changePassword(long id, String oldPassword, String newPassword) {
		System.out.println("in service layer change password");
		LabIncharge labIncharge = labInchargeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		labIncharge.setPassword(newPassword);
		return "password changed successfully";
	}

	
	@Override
	public String addLabTest(LabTestsDTO labTestDTO) {
		System.out.println("in service layer add medicine");
		LabTests labTest = mapper.map(labTestDTO, LabTests.class);
		labTestRepo.save(labTest);
		return "lab test added successfully";
	}
	
	@Override
	public String deleteLabTest(long id) {
		labTestRepo.deleteById(id);
		return "lab test deleted";
	}
	

}
