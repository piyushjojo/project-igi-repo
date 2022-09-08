package com.example.demo.pojos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medicine_prescription")
public class MedicinePrescription extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "med_id" , nullable = false )
	private Medicine medicine ; 
	
	private int quantity ; 
}
