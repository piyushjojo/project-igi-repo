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
import com.app.dto.EmailDetails;
import com.app.dto.OrderHistoryResponseDTO;
import com.app.dto.OrderMedicineRequestDTO;
import com.app.dto.OrderMedicineResponseDTO;
import com.app.dto.PaymentProcessingDto;
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
	@Autowired
	private EmailService emailService;
	

	@Override
	public Order saveOrderDetails(long id, ArrayList<OrderMedicineRequestDTO> orderList) {
		Patient patient=patientRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not valid"));;
		Order o=orderRepo.save(new Order(patient));
		System.out.println("cheking o "+o);
		Order order=orderRepo.findByPatient(patient,o.getId());
		System.out.println("cheking order "+order);
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
		
		return order;
	}
	
	@Override
	public OrderMedicineResponseDTO fetchOMRSD(Order order) {
		OrderMedicineResponseDTO omrsd=new OrderMedicineResponseDTO(medOrdRepo.findByOrder(order),orderRepo.findById(order.getId()).orElseThrow(() -> new ResourceNotFoundException("Not valid")));
		System.out.println("------------------------------------------");
		System.out.println(omrsd);
		System.out.println("------------------------------------------");
		return omrsd;
	}
	
	@Override
	public OrderMedicineResponseDTO paymentUpdateDetails(PaymentProcessingDto paymentDto,long id) {
		orderRepo.updateOrderStatusDetails(paymentDto.getOrderId());
		patientRepo.updatePatientWallet(paymentDto.getOrderAmount(), id);
		Order order=orderRepo.findById(paymentDto.getOrderId()).orElseThrow(() -> new ResourceNotFoundException("Not valid"));
		OrderMedicineResponseDTO omrsd = fetchOMRSD(order);
		Patient p=patientRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not valid"));
		System.out.println("Cheking p "+p);
		String msgBody="Payment Successfull\n";
		msgBody+="Order id: "+order.getId()+"\n";
		msgBody+="Order Date: "+order.getOrder_date()+"\n";
		msgBody+="Order Amount: "+order.getAmount()+"\n";
		msgBody+="Payment Status: "+order.getPayment_status()+"\n";
		msgBody+="Order Status: "+order.getOrder_status()+"\n";
		msgBody+="\n";
		msgBody+="\n";
		msgBody+="\n";
		msgBody+="-----------------------------------------\n";
//		double walletBalance=p.getWallet()-order.getAmount();
		msgBody+="Wallet Balance: "+p.getWallet()+"\n";
		msgBody+="Thank You For Ordering "+"\n";
		String subject="Order Details";
		EmailDetails details=new EmailDetails(p.getEmail(),msgBody,subject);
//		String sendSimpleMail = emailService.sendSimpleMail(details);
//		System.out.println(sendSimpleMail);
		return omrsd;
	}
	
	@Override 
	public OrderHistoryResponseDTO fetchOrderlist(long id) {
		System.out.println("fetch order list");
		return new OrderHistoryResponseDTO(orderRepo.findByPatientId(id));
	}
	
	@Override
	public void updateOrderStatus(long orderId) {
		orderRepo.updateOrderStatus(orderId);
	}

}
