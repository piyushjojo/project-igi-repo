package com.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MedicineDTO {
	@NotBlank(message = "can not be blank")
	private String name;

	private double price;

	private int quantity;
	@NotBlank(message = "can not be blank")
	private String manufacturer;

}
