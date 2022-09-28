package com.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.MedInchargeRepository;
import com.app.dao.PatientRepository;
import com.app.pojos.MedicineIncharge;
import com.app.pojos.Patient;

@Service
public class SignInService implements UserDetailsService {

	@Autowired
	private MedInchargeRepository medInchargeRepo;

	@Autowired
	private PatientRepository patientRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			System.out.println("in med incharge loadbyusername " + username);
			String[] arr = username.split(":");
			if (arr[1].equals("MED")) {
				MedicineIncharge userEnt = medInchargeRepo.findByEmail(arr[0]);
				System.out.println("in load service sop username : " + arr[0] + " and user ent " + arr[1]);
				return new org.springframework.security.core.userdetails.User(userEnt.getEmail(), userEnt.getPassword(),
						new ArrayList<>());
			} else if (arr[1].equals("PATIENT")) {
				Patient userEnt = patientRepo.findActiveUser(arr[0]);
				System.out.println("in load service sop username : " + arr[0] + " and user ent " + arr[1]);
				return new org.springframework.security.core.userdetails.User(userEnt.getEmail(), userEnt.getPassword(),
						new ArrayList<>());
			}
			return null;
		} catch (Exception e) {
			throw new UsernameNotFoundException("Error Fetching User Data", e);
		}
	}

}
