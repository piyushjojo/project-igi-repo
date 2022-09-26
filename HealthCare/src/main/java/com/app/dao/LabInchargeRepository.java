package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.LabIncharge;

public interface LabInchargeRepository extends JpaRepository<LabIncharge, Long>{
	@Query(nativeQuery = true ,value = "select * from lab_incharge li where li.email = ?1 and binary li.password = ?2 ")
	Optional<LabIncharge> findByEmailAndPassword(String email, String pass);
	
//	MedicineIncharge findByEmail(String email);
//	
//	@Query("select p.id from Patient p where p.email = ?1 and p.status = true ")
//	long getMedicineInchargeId(String email);
	
}
