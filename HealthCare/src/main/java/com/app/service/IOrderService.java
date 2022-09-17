package com.app.service;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.dto.OrderMedicineRequestDTO;
import com.app.dto.OrderMedicineResponseDTO;
import com.app.dto.PaymentProcessingDto;
import com.app.pojos.Order;

public interface IOrderService {
	Order saveOrderDetails(long id,ArrayList<OrderMedicineRequestDTO> orderList);

	OrderMedicineResponseDTO fetchOMRSD(Order order);

	OrderMedicineResponseDTO paymentUpdateDetails(PaymentProcessingDto paymentDto,long id);
	
	
}
