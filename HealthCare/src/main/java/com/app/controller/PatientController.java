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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.config.TokenManager;
import com.app.dao.PatientRepository;
import com.app.dto.ApiResponse;
import com.app.dto.ChangePasswordDTO;
import com.app.dto.LoginRequestDTO;
import com.app.dto.LoginResponseDTO;
import com.app.dto.OrderMedicineRequestDTO;
import com.app.dto.OrderMedicineResponseDTO;
import com.app.dto.PatientEmailDTO;
import com.app.dto.PatientSignUpRequest;
import com.app.dto.PaymentProcessingDto;
import com.app.dto.ProfileDTO;
import com.app.dto.RequestValidateToken;
import com.app.dto.WalletRechargeDTO;
import com.app.pojos.Medicine;
import com.app.pojos.Order;
import com.app.pojos.Patient;
import com.app.service.IMedicineService;
import com.app.service.IOrderService;
import com.app.service.IPatientService;
import com.app.service.PatientService;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

	@Autowired
	private IPatientService patientService;

	@Autowired
	private PatientRepository patientRepo;

	@Autowired
	private IMedicineService medService;

	@Autowired
	private IOrderService orderService;

	public PatientController() {
		System.out.println("in patient controller " + getClass());
	}

	// user signup to check if email exist.
	@PostMapping("/signup1")
	public ResponseEntity<?> signUp(@RequestBody @Valid PatientEmailDTO patientEmailDTO) {
		System.out.println("in signup email confirmation page");
		Patient patient = patientRepo.findActiveUser(patientEmailDTO.getEmail());
		if (patient == null) {
			return new ResponseEntity<>(new ApiResponse("new user"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ApiResponse("user already exist"), HttpStatus.BAD_REQUEST);
	}

	// user signup after email confirmed
	@PostMapping("/signup2")
	public ResponseEntity<?> signUp(@RequestBody @Valid PatientSignUpRequest request) {
		System.out.println("in user signup ");
		try {
			return ResponseEntity.ok(new ApiResponse(patientService.signUp(request)));
		} catch (RuntimeException e) {
			System.out.println("err in add emp " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Credentials"), HttpStatus.BAD_REQUEST);
		}
	}

	// to get profile for user. user-id is passed in url path
	@GetMapping("/profile/{id}")
	public ProfileDTO patientProfile(@PathVariable long id) {
		System.out.println("in patient profile ");
		System.out.println(patientService.showProfile(id));
		return patientService.showProfile(id);
	}

	// to change user password
	@PutMapping("/profile/changePassword/{id}")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO pcp, @PathVariable long id) {
		System.out.println("in  controller change password");
		try {
			patientService.changePassword(id, pcp.getOldPassword(), pcp.getNewPassword());
			return new ResponseEntity<>(new ApiResponse("pasword changed successfully"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ApiResponse("Invalid old password"), HttpStatus.BAD_REQUEST);
		}
	}

	// to delete account
	@PutMapping("/profile/delete/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable long id) {
		System.out.println("in delete user ");
		patientService.deleteUser(id);
		return new ResponseEntity<>(new ApiResponse("user deleted successfully"), HttpStatus.OK);
	}

	@GetMapping("/ordermed")
	public ResponseEntity<?> getMedicineList(@RequestParam String name) {
		System.out.println(name);
		ArrayList<Medicine> medList = medService.findByMedicineName(name);
		System.out.println(medList);
		return new ResponseEntity<>(medList, HttpStatus.OK);
	}

	@PostMapping("/order/{id}")
	public ResponseEntity<?> checkOutOrder(@PathVariable long id,
			@RequestBody ArrayList<OrderMedicineRequestDTO> orderList) {
		System.out.println(orderList);
		Order order = orderService.saveOrderDetails(id, orderList);
		OrderMedicineResponseDTO omrsd = orderService.fetchOMRSD(order);

		return new ResponseEntity<>(omrsd, HttpStatus.OK);
	}

	@PostMapping("/payment/{id}")
	public ResponseEntity<?> paymentProcessing(@PathVariable long id, @RequestBody PaymentProcessingDto paymentDto) {
		System.out.println("In payment Processing " + paymentDto);
		OrderMedicineResponseDTO omrsd = orderService.paymentUpdateDetails(paymentDto, id);
		System.out.println(omrsd);
		return new ResponseEntity<>(omrsd, HttpStatus.OK);
	}

	@PutMapping("/walletRecharge/{id}")
	public ResponseEntity<?> recharge(@PathVariable long id, @RequestBody WalletRechargeDTO rec) {
		System.out.println("in wallet reacharge" + id + " " + rec);
		try {
			patientService.walletRecharge(id, rec.getAmt());
			return new ResponseEntity<>(new ApiResponse("amount updated successfully"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ApiResponse("transaction failed"), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/orderhistory/{id}")
	public ResponseEntity<?> fetchOrderList(@PathVariable long id) {
		System.out.println(orderService.fetchOrderlist(id) + " in controller fetch orderlist");
		return new ResponseEntity<>(orderService.fetchOrderlist(id), HttpStatus.OK);
	}

	@GetMapping("/wallet/{id}")
	public ResponseEntity<?> getWallet(@PathVariable long id) {
		Patient p = patientService.getWallet(id);
		return new ResponseEntity<>(p.getWallet(), HttpStatus.OK);
	}

}
