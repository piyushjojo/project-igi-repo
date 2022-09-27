package com.app.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
	@Modifying
	@Query("update Medicine m set m.quantity = m.quantity + ?2 where m.id = ?1 ")
	void udpateMedQty(long id, int qty);
	
	@Query("select m from Medicine m where m.name like %?1%")
	ArrayList<Medicine> findByMNameLike(String name);
//	ArrayList<Medicine> findById(long id);
	
	
	Medicine findByName(String name);
	
	@Modifying
	@Query("update Medicine m set m.quantity = m.quantity - ?2 where m.id = ?1 ")
	void reduceMedQty(long id, int qty);

}
