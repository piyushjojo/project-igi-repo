package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "medicine")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Medicine extends BaseEntity{
	
	private String name; 
	private double price; 
	private int quantity; 
	private String manufacturer; 

//	private String description ; 
//	private String category ; 
//	private String diseases ; 
//	private String side_effects ; 
}
