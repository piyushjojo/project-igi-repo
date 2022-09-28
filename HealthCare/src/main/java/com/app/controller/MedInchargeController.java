package com.app.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.config.TokenManager;
import com.app.dao.IOrderRepository;
import com.app.dto.ApiResponse;
import com.app.dto.ChangePasswordDTO;
import com.app.dto.LoginRequestDTO;
import com.app.dto.LoginResponseDTO;
import com.app.dto.MedQtyUpdateDTO;
import com.app.dto.MedicineDTO;
import com.app.dto.ProfileDTO;
import com.app.dto.RequestValidateToken;
import com.app.pojos.MedicineIncharge;
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

	@PutMapping("/updateQty/{id}")
	public ResponseEntity<?> updateQty(@PathVariable long id , @RequestBody MedQtyUpdateDTO medQtyDTO){
		return new ResponseEntity<>(new ApiResponse(medInchargeService.updateQty(id , medQtyDTO)), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteMed/{id}") //update quantity
	public ResponseEntity<?> deleteMedicine(@PathVariable long id){
		System.out.println(id);
		return new ResponseEntity<>(new ApiResponse(medInchargeService.deleteMedicine(id)), HttpStatus.OK);
	}
	
	
	@GetMapping("/fetchorders")
	public ResponseEntity<?> fetchOrders(){
		
	return new ResponseEntity<>(orderRepo.fetchOrderList(), HttpStatus.OK);
	}
	
	@PutMapping("/updateorder/{id}")
	
	public ResponseEntity<?> updateOrderStatus(@PathVariable long id){
		orderService.updateOrderStatus(id);
		return new ResponseEntity<>(new ApiResponse("Order Dispatched"), HttpStatus.OK);
	}
	
}
