package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.PatientRepository;
import com.app.dto.PatientLoginRequest;
import com.app.dto.PatientLoginResponse;
import com.app.pojos.Patient;

@Service
@Transactional
public class PatientService implements IPatientService {

	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public PatientLoginResponse login(PatientLoginRequest request) {
		Patient patient = patientRepo.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		System.out.println(patient);
		// => valid login
		// Patient : PERSISTENT
		// API of ModelMapper : public Object map(Object src,Class<T> destType)
		// Mapping from Entity --> DTO
		PatientLoginResponse resp = mapper.map(patient, PatientLoginResponse.class);
		System.out.println(resp);
		return resp;
	}

}
