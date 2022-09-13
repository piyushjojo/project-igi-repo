package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
	@Modifying
	@Query("update Medicine m set m.quantity = m.quantity + ?2 where m.id = ?1 ")
	void udpateMedQty(long id, int qty);

}
