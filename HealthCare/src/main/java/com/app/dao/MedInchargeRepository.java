package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.MedicineIncharge;

public interface MedInchargeRepository extends JpaRepository<MedicineIncharge, Long>{
	@Query(nativeQuery = true ,value = "select * from medicine_incharge mi where mi.email = ?1 and binary mi.password = ?2 ")
	Optional<MedicineIncharge> findByEmailAndPassword(String email, String pass);
	
//	MedicineIncharge findByEmail(String email);
//	
//	@Query("select p.id from Patient p where p.email = ?1 and p.status = true ")
//	long getMedicineInchargeId(String email);
	
}
