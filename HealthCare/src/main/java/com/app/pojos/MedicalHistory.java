package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medical_history")
public class MedicalHistory {
	
	@Id
	private String id;
	
	@OneToOne
	@JoinColumn(name = "patient_email" , nullable = false)
	@MapsId
	private Patient patient ; 
	
	private double height ;
	
	private double weight ; 
	
	@Enumerated(EnumType.STRING)
	@Column(name = "blood_group")
	private BloodGroup bloodGroup ;
	
	@Column(name = "chronic_disease")	
	private String chronicDisease ; 
	
	private String prev_ops ; 
	
	private String allergies ; 
	
	
}
