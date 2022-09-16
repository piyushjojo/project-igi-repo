package com.app.service;

import java.util.ArrayList;

import com.app.dto.OrderMedicineRequestDTO;

public interface IOrderService {
	String saveOrderDetails(long id,ArrayList<OrderMedicineRequestDTO> orderList);
}
