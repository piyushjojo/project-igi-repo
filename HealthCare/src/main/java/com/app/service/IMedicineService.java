package com.app.service;

import java.util.ArrayList;

import com.app.pojos.Medicine;

public interface IMedicineService {
	ArrayList<Medicine> findByMedicineName(String name);

	void updateMedicineQty(long id, int qty);

}
