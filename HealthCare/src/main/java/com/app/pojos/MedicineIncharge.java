package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "medicine_incharge")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MedicineIncharge extends BaseEntity {
	
	private String email ; 
	
	private String name ; 
	private long phone_no ; 
	private String address ; 
	private LocalDate dob ; 
	private Gender gender ; 
	//photopath 
	
	private String password ; 

}
