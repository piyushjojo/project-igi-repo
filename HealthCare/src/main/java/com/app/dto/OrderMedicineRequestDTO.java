package com.app.dto;

import com.app.pojos.Medicine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderMedicineRequestDTO {
private Medicine med;
private int quantity;
}
