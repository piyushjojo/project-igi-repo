package com.example.demo.pojos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medicine_order")
public class MedicineOrder extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "order_no")
	private Order order ; 
	
	@ManyToOne
	@JoinColumn(name = "med_id" , nullable = false)
	private Medicine medicine ;
	
	private double amount ; 
	
	private boolean is_prescribed ; 
	
	private int quantity ; 
	
}
