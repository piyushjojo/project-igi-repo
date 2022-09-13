package com.app.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.custom_exceptions.UserAlreadyExists;
import com.app.dao.PatientRepository;
import com.app.dto.PatientLoginRequest;
import com.app.dto.PatientLoginResponse;
import com.app.dto.PatientProfileDTO;
import com.app.dto.PatientSignUpRequest;
import com.app.pojos.Patient;

@Service
@Transactional
public class PatientService implements IPatientService {

	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public long getPatientId(String email) {
		return patientRepo.getPatientId(email);
	}
	
	@Override
	public String signUp(PatientSignUpRequest request) {
		Patient patient = patientRepo.findByEmail(request.getEmail());
		if(patient == null || !patient.isStatus()) {
			patient = mapper.map(request, Patient.class);
			patientRepo.save(patient);
			return "patient added successfully";
		}
		throw new UserAlreadyExists("User already exist.");
	}
	
	@Override
	public PatientLoginResponse login(PatientLoginRequest request) {
		Patient patient = patientRepo.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		System.out.println(patient);
		
		PatientLoginResponse resp = mapper.map(patient, PatientLoginResponse.class);
		System.out.println(resp);
		return resp;
	}

	@Override
	public PatientProfileDTO showProfile(long id) {
		Patient patient = patientRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		System.out.println(patient);
		PatientProfileDTO patientProfileDTO = mapper.map(patient, PatientProfileDTO.class);
		System.out.println(patientProfileDTO);
		return patientProfileDTO;
	}

	@Override
	public String changePassword(long id, String oldPassword, String newPassword) {
		System.out.println("in service layer change password");
		Patient patient = patientRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		patient.setPassword(newPassword);
		return "password changed successfully";
	}

	@Override
	public String deleteUser(long id) {
		Patient patient = patientRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		patient.setStatus(false);
		return "patient deleted";
	}
	
	

}
