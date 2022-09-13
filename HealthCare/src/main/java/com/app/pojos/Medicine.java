package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "medicine")
@Getter
@Setter
@ToString
public class Medicine extends BaseEntity{
	
	private String name ; 
	private String description ; 
	private double price ; 
	private int quantity ; 
	private String category ; 
	private String diseases ; 
	private String side_effects ; 
	private String manufacturer ; 
}
