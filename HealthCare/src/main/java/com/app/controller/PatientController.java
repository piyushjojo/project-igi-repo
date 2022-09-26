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
import org.springframework.security.core.userdetails.UserDetailsService;
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

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

	@Autowired
	private IPatientService patientService;
	
	@Autowired
	private PatientRepository patientRepo ; 
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationConfiguration authenticationManager;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private IMedicineService medService;
	
	@Autowired
	private IOrderService orderService;
	


	public PatientController() {
		System.out.println("in patient controller " + getClass());
	}
	
	//user signup 
	@PostMapping("/signup1")
	public ResponseEntity<?> signUp(@RequestBody @Valid PatientEmailDTO patientEmailDTO){
		System.out.println("in signup email confirmation page");
		Patient patient = patientRepo.findActiveUser(patientEmailDTO.getEmail());
		if(patient == null) {
			return new ResponseEntity<>(new ApiResponse("new user"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ApiResponse("user already exist"), HttpStatus.BAD_REQUEST);
	}
	//otp feature add here

	//how to get email from signup1 to signup2 
	@PostMapping("/signup2")
	public ResponseEntity<?> signUp(@RequestBody  @Valid PatientSignUpRequest request){
		System.out.println("in user signup ");
		try {
			return ResponseEntity.ok(new ApiResponse(patientService.signUp(request)));
		} catch (RuntimeException e) {
			System.out.println("err in add emp " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Credentials"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> createToken(@RequestBody LoginRequestDTO request) throws Exception {
		try {
			System.out.println("in create token");
			authenticationManager.getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		} catch (DisabledException e) {

			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			System.out.println("in INVALID_CREDENTIALS ");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		
		 Patient u= patientService.findByEmail(request.getEmail());
		if(!u.isStatus()){
			return new ResponseEntity<>(new ApiResponse("Invalid Credentials"), HttpStatus.BAD_REQUEST);
		}
		final String jwtToken = tokenManager.generateJwtToken(userDetails);
		return ResponseEntity.ok(new LoginResponseDTO(jwtToken,u.getId(), u.getName(),  u.getEmail() , u.getWallet()));
	}
	
	@PostMapping("/validate-token")
	public ResponseEntity<?> validateToken(@RequestBody RequestValidateToken tokenRequest) throws Exception {

		return ResponseEntity
				.ok(tokenManager.validateJwtToken(tokenRequest.getToken(), User.withUsername(tokenRequest.getUsername())
						.authorities(new ArrayList<SimpleGrantedAuthority>()).password("").build()));
	}

	
//	// add REST end point for user login
//	@PostMapping("/signin")
//	public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequestDTO request,
//			HttpSession httpSession) {
//		System.out.println("in user login " + request);
////		long id = patientService.getPatientId(request.getEmail());
//		try {
//			LoginResponseDTO response = patientService.login(request);
//			httpSession.setAttribute("patient_login_response", response);
//			return ResponseEntity.ok(response);
//		} catch (RuntimeException e) {
//			System.out.println("err in add emp " + e);
//			return new ResponseEntity<>(new ApiResponse("Invalid Credentials"), HttpStatus.BAD_REQUEST);
//		}
//	}

	@GetMapping("/profile/{id}")
	public ProfileDTO patientProfile(@PathVariable long id) {
		System.out.println("in patient profile ");
		System.out.println(patientService.showProfile(id));
		return patientService.showProfile(id);
	}
	
	@PutMapping("/profile/changePassword/{id}")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO pcp ,@PathVariable long id){
		System.out.println("in  controller change password");
		try {
			patientService.changePassword(id , pcp.getOldPassword(), pcp.getNewPassword());
			return new ResponseEntity<>(new ApiResponse("pasword changed successfully"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ApiResponse("Invalid old password"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/profile/delete/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable long id){
		System.out.println("in delete user ");
		patientService.deleteUser(id);
		return new ResponseEntity<>(new ApiResponse("user deleted successfully"), HttpStatus.OK);
	}

	@PostMapping("/signout")
	public ResponseEntity<?> signout(HttpSession httpSession) {
		//LoginResponseDTO patient = (LoginResponseDTO) httpSession.getAttribute("patient_login_response");
		httpSession.invalidate();
		return new ResponseEntity<>(new ApiResponse(" Logged out Successfully"), HttpStatus.OK);
	}
	
	@GetMapping("/ordermed")
	public ResponseEntity<?> getMedicineList(@RequestParam String name){
		System.out.println(name);
		ArrayList<Medicine> medList = medService.findByMedicineName(name);
		System.out.println(medList);
		return new ResponseEntity<>(medList, HttpStatus.OK);
	}
	
	@PostMapping("/order/{id}")
	public ResponseEntity<?> checkOutOrder(@PathVariable long id,@RequestBody ArrayList<OrderMedicineRequestDTO> orderList){
		System.out.println(orderList);
		Order order = orderService.saveOrderDetails(id, orderList);
		OrderMedicineResponseDTO omrsd = orderService.fetchOMRSD(order);
		
		return new ResponseEntity<>(omrsd, HttpStatus.OK);
	}
	
	@PostMapping("/payment/{id}")
	public ResponseEntity<?> paymentProcessing(@PathVariable long id,@RequestBody PaymentProcessingDto paymentDto){
		System.out.println("In payment Processing "+paymentDto);
		OrderMedicineResponseDTO omrsd =orderService.paymentUpdateDetails(paymentDto, id);
		System.out.println(omrsd);
		return new ResponseEntity<>(omrsd,HttpStatus.OK);
	}
	
	@PutMapping("/walletRecharge/{id}")
	public ResponseEntity<?> recharge(@PathVariable long id , @RequestBody WalletRechargeDTO rec){
		System.out.println("in wallet reacharge");
		try {
			patientService.walletRecharge(id , rec.getAmount());
			return new ResponseEntity<>(new ApiResponse("amount updated successfully"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ApiResponse("transaction failed"), HttpStatus.BAD_REQUEST);
		}
	}
	

	
	@GetMapping("/orderhistory/{id}")
		public ResponseEntity<?> fetchOrderList(@PathVariable long id){
		System.out.println(orderService.fetchOrderlist(id)+" in controller fetch orderlist");
		return new ResponseEntity<>(orderService.fetchOrderlist(id), HttpStatus.OK);
	}

}
