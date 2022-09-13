package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
//add user authentication method : finder method
	Optional<Patient> findByEmailAndPassword(String email, String pass);
	
	Patient findByEmail(String email);
	
	@Query("select p.id from Patient p where p.email = ?1 and p.status = true ")
	long getPatientId(String email);

}
