package com.app.service;

import java.util.ArrayList;

import com.app.dto.OrderMedicineRequestDTO;
import com.app.pojos.Medicine;
import com.app.pojos.Order;

public interface IMedicineService {
	ArrayList<Medicine> findByMedicineName(String name);
	
	void updateMedicineQty(long id,int qty);
	
}
