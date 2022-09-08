package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointments extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "pEmail", nullable = false)
	private Patient patient ; 
	
	@ManyToOne
	@JoinColumn(name = "doctor_email" , nullable = false)
	private Doctor doctor ; 
	
	
	private AppointmentType type ;
	
	private String speciality;
	
	private String report_title ;
	
	private String report_remarks ; 
	
	private PaymentStatus payment_status ; 
	
	private LocalDate schedule_date_time ; 
	
}
