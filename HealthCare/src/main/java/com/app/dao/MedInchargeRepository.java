package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.MedicineIncharge;

public interface MedInchargeRepository extends JpaRepository<MedicineIncharge, Long> {
	@Query(nativeQuery = true, value = "select * from medicine_incharge mi where mi.email = ?1 and binary mi.password = ?2 ")
	Optional<MedicineIncharge> findByEmailAndPassword(String email, String pass);

	@Query("select m from MedicineIncharge m where m.email = ?1")
	MedicineIncharge findByEmail(String username);

}
