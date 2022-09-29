package com.app.controller;

import javax.validation.Valid;

import com.app.dao.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IMedicineOrderRepo;
import com.app.dao.IOrderRepository;
import com.app.dto.ApiResponse;
import com.app.dto.ChangePasswordDTO;
import com.app.dto.FetchOrderListResponseDTO;
import com.app.dto.MedQtyUpdateDTO;
import com.app.dto.MedicineDTO;
import com.app.dto.ProfileDTO;
import com.app.pojos.Order;
import com.app.service.IOrderService;
import com.app.service.MedInchargeService;

@RestController
@RequestMapping("/medincharge")
@CrossOrigin(origins = "http://localhost:3000")
public class MedInchargeController {
	
	@Autowired
	private MedInchargeService medInchargeService ; 
	
	@Autowired
	private IOrderRepository orderRepo;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IMedicineOrderRepo medRepo;

	@Autowired
	private MedicineRepository medicineRepository;

	@GetMapping("/profile/{id}")
	public ProfileDTO patientProfile(@PathVariable long id) {
		System.out.println("in med incharge profile ");
		System.out.println(medInchargeService.showProfile(id));
		return medInchargeService.showProfile(id);
	}
	
	@PutMapping("/profile/changePassword/{id}")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO pcp ,@PathVariable long id){
		System.out.println("in  controller change password");
		try {
			medInchargeService.changePassword(id , pcp.getOldPassword(), pcp.getNewPassword());
			return new ResponseEntity<>(new ApiResponse("pasword changed successfully"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ApiResponse("Invalid old password"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/addMedicine")
	public ResponseEntity<?> addMedicine(@RequestBody @Valid MedicineDTO medicineDTO){
		System.out.println("in add medicine");
		try {
			medInchargeService.addMedicine(medicineDTO);
			return new ResponseEntity<>(new ApiResponse("medicine added successfully"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ApiResponse("failed to add medicine"), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/addMedicine")
	public ResponseEntity<?> updateMedicine(@RequestBody @Valid MedicineDTO medicineDTO){
		System.out.println("in add medicine");
		try {
			medInchargeService.addMedicine(medicineDTO);
			return new ResponseEntity<>(new ApiResponse("medicine added successfully"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ApiResponse("failed to add medicine"), HttpStatus.BAD_REQUEST);
		}
	}



//	@PutMapping("/updateQty/{id}")
//	public ResponseEntity<?> updateQty(@PathVariable long id , @RequestBody MedQtyUpdateDTO medQtyDTO){
//		return new ResponseEntity<>(new ApiResponse(medInchargeService.updateQty(id , medQtyDTO)), HttpStatus.OK);
//	}
	
	@DeleteMapping("/deleteMed/{id}") //update quantity
	public ResponseEntity<?> deleteMedicine(@PathVariable long id){
		System.out.println(id);
		return new ResponseEntity<>(new ApiResponse(medInchargeService.deleteMedicine(id)), HttpStatus.OK);
	}
	
	
	@GetMapping("/fetchorders")
	public ResponseEntity<?> fetchOrders(@RequestParam int pageno){
		Pageable pageable=PageRequest.of(pageno, 5);
		System.out.println(pageable+" in pageable");
		Page<Order> orderlist=orderRepo.fetchOrderList(pageable);
		System.out.println(orderlist.toList()+" abc "+orderlist.getTotalPages());
	return new ResponseEntity<>(new FetchOrderListResponseDTO(orderlist.toList(),orderlist.getTotalPages()), HttpStatus.OK);
	}
	
	@PutMapping("/updateorder/{id}")
	
	public ResponseEntity<?> updateOrderStatus(@PathVariable long id){
		orderService.updateOrderStatus(id);
		return new ResponseEntity<>(new ApiResponse("Order Dispatched"), HttpStatus.OK);
	}
	
	@GetMapping("/vieworder/{id}")
	public ResponseEntity<?> getOrderByOrderId(@PathVariable long id){
		
		return new ResponseEntity<>(medRepo.findAllByOrderId(id), HttpStatus.OK);
		}


		@GetMapping("/medicines")
		public ResponseEntity<?> getAllMedicines(){
		return new ResponseEntity<>(medicineRepository.findAll(), HttpStatus.OK);
		}
	
}
