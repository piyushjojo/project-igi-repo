package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	//add user authentication method : finder method
	@Query(nativeQuery = true ,value = "select * from patient_profile p where p.email = ?1 and binary p.password = ?2 ")
	Optional<Patient> findByEmailAndPassword(String email, String pass);
	
	Patient findByEmail(String email);
	
	@Query("select p.id from Patient p where p.email = ?1 and p.status = true ")
	long getPatientId(String email);
	
	@Modifying
	@Query("update Patient p set p.wallet=p.wallet-?1 where p.id=?2")
	void updatePatientWallet(double amount,long id); 

}
