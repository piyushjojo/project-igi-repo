package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_table")
@Getter
@Setter
@ToString
public class Order extends BaseEntity{
	
	
	@ManyToOne
	@JoinColumn(name = "patient_id" )
	private Patient patient ; 
	
	private double amount ; 
	
	@Enumerated(EnumType.STRING)
	private OrderStatus order_status ; 
	
	private LocalDate order_date ;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus payment_status ; 
}
