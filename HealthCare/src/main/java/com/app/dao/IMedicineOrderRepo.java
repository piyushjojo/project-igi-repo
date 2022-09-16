package com.app.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.MedicineOrder;

public interface IMedicineOrderRepo extends JpaRepository<MedicineOrder, Long> {
	@Query("select m.amount from MedicineOrder m where m.order.id=?1")
	ArrayList<Double> findAllAmountById(long id);

}
