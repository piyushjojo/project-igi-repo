package com.app.service;

import com.app.dto.PatientLoginRequest;
import com.app.dto.PatientLoginResponse;

public interface IPatientService {
	PatientLoginResponse login(PatientLoginRequest request);
}
