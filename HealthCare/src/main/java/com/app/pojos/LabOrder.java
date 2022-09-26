package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "lab_order")
@Getter
@Setter
@ToString
public class LabOrder extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "order_no" )
	private Order order ; 
	
	@ManyToOne
	@JoinColumn(name = "lab_test_id" , nullable =  false )
	private LabTests lab_test;
	
	private String lab_name ; 
	
	private LocalDate schedule ; 
	
	private double amount ; 
	
	private boolean is_prescribed ; 
	
}
