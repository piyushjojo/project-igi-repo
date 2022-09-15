package com.app.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.MedicineRepository;
import com.app.pojos.Medicine;
@Service
@Transactional
public class MedicineService implements IMedicineService {
	
	@Autowired
	private MedicineRepository medRepo;
	
	@Override
	public ArrayList<Medicine> findByMedicineName(String name){
		System.out.println(name+" In service");
		System.out.println(medRepo.findByMNameLike(name)+" In service");
		return medRepo.findByMNameLike(name);
	}

}
