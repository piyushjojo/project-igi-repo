package com.app.service;

import java.util.ArrayList;

import com.app.dto.OrderMedicineRequestDTO;
import com.app.pojos.Order;

public interface IMedicineOrderService {

	String addOrderInMedicine(Order order, ArrayList<OrderMedicineRequestDTO> orderList);

}
