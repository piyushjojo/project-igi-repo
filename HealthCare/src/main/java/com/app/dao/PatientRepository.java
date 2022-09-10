package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {
//add user authentication method : finder method
	Optional<Patient> findByEmailAndPassword(String email, String pass);

	
//
//	@Query("select u from User u join fetch u.address a where u.id=?1")
//	Optional<User> getUserNAddressDetails(long userId);

}
