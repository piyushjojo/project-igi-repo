package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.Table;

//import pojos.Address;
@Entity
@Table(name = "lab_tests")
public class LabTests extends BaseEntity {
	
	private String lab_name ; 
	private String test_name ; 
	private double amount   ; 
	private String description  ; 
	//private Address address ; 
}
