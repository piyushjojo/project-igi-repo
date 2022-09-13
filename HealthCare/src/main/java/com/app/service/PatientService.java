package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.custom_exceptions.UserAlreadyExists;
import com.app.dao.PatientRepository;
import com.app.dto.LoginRequestDTO;
import com.app.dto.LoginResponseDTO;
import com.app.dto.PatientSignUpRequest;
import com.app.dto.ProfileDTO;
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
	public LoginResponseDTO login(LoginRequestDTO request) {
		Patient patient = patientRepo.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		System.out.println(patient);
		
		LoginResponseDTO resp = mapper.map(patient, LoginResponseDTO.class);
		System.out.println(resp);
		return resp;
	}

	@Override
	public ProfileDTO showProfile(long id) {
		Patient patient = patientRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		System.out.println(patient);
		ProfileDTO profileDto = mapper.map(patient, ProfileDTO.class);
		System.out.println(profileDto);
		return profileDto;
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
