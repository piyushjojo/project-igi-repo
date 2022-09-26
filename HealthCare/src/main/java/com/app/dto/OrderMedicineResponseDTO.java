package com.app.dto;

import java.util.ArrayList;

import com.app.pojos.Medicine;
import com.app.pojos.MedicineOrder;
import com.app.pojos.Order;

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
public class OrderMedicineResponseDTO {
private ArrayList<MedicineOrder> medList;
private Order order;
}
