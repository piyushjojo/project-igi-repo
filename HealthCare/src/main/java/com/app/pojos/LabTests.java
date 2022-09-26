package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//import pojos.Address;
@Entity
@Table(name = "lab_tests")
@Getter
@Setter
@ToString
public class LabTests extends BaseEntity {
	
	private String lab_name ; 
	private String test_name ; 
	private double price   ; 
//	private String description  ; 
	//private Address address ; 
}
