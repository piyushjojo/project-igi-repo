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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "medical_history")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class MedicalHistory extends BaseEntity{
	
	@OneToOne
	@JoinColumn(name = "patient_id" , nullable = false)
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
