package com.app.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IMedicineOrderRepo;
import com.app.dto.OrderMedicineRequestDTO;
import com.app.pojos.MedicineOrder;
import com.app.pojos.Order;

@Service
@Transactional
public class MedicineOrderService implements IMedicineOrderService {

	@Autowired
	private IMedicineOrderRepo medOrdRepo;
	@Autowired
	private IMedicineService medService;

	@Override
	public String addOrderInMedicine(Order order, ArrayList<OrderMedicineRequestDTO> orderList) {

		for (OrderMedicineRequestDTO omrd : orderList) {
			MedicineOrder medOrder = new MedicineOrder(order, omrd.getMed(),
					omrd.getQuantity() * omrd.getMed().getPrice(), true, omrd.getQuantity());
			medOrdRepo.save(medOrder);
			medService.updateMedicineQty(omrd.getMed().getId(), omrd.getQuantity());
		}
		return null;
	}
}
