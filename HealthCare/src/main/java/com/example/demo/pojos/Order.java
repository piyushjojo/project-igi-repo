package com.example.demo.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order")
public class Order extends BaseEntity{
	
	
	@ManyToOne
	@JoinColumn(name = "pEmail" , nullable = false)
	private Patient patient ; 
	
	private double amount ; 
	
	@Enumerated(EnumType.STRING)
	private OrderStatus order_status ; 
	
	private LocalDate order_date ;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus payment_status ; 
}
