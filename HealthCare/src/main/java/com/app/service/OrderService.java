package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.IMedicineOrderRepo;
import com.app.dao.IOrderRepository;
import com.app.dao.PatientRepository;
import com.app.dto.OrderMedicineRequestDTO;
import com.app.pojos.Order;
import com.app.pojos.OrderStatus;
import com.app.pojos.Patient;
import com.app.pojos.PaymentStatus;

@Service
@Transactional
public class OrderService implements IOrderService {
	
	@Autowired
	private IOrderRepository orderRepo;
	@Autowired
	private PatientRepository patientRepo;
	@Autowired
	private IMedicineOrderService medOrdService;
	@Autowired
	private IMedicineOrderRepo medOrdRepo;

	@Override
	public String saveOrderDetails(long id, ArrayList<OrderMedicineRequestDTO> orderList) {
		Patient patient=patientRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not valid"));;
		orderRepo.save(new Order(patient));
		Order order=orderRepo.findByPatient(patient);
		System.out.println(patient+" "+order+"Before Adding in medtable ");
		medOrdService.addOrderInMedicine(order, orderList);
		System.out.println("Before Sum");
		ArrayList<Double> list=medOrdRepo.findAllAmountById(order.getId());
		double sum=0;
		for(double d:list) {
			sum+=d;
		}
		System.out.println(sum+" After sum");
		orderRepo.updateOrder(sum, LocalDate.now(), OrderStatus.INITIATED, PaymentStatus.UNPAID, order.getId());
		
		return "Order added successfully";
	}

}
