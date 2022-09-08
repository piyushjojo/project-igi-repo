package com.example.demo.pojos;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "medicine")
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
