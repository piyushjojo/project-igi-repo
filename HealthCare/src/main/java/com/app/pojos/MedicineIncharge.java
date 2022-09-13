package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

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
	@Enumerated(EnumType.STRING)
	private Gender gender ; 
	
	private String password ; 

}
