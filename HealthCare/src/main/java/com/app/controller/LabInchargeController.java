package com.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.ChangePasswordDTO;
import com.app.dto.LabTestsDTO;
import com.app.dto.LoginRequestDTO;
import com.app.dto.LoginResponseDTO;
import com.app.dto.ProfileDTO;
import com.app.service.ILabInchargeService;

@RestController
@RequestMapping("/labincharge")
@CrossOrigin(origins = "http://localhost:3000")
public class LabInchargeController {
	
	@Autowired
	private ILabInchargeService labInchargeService ; 
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequestDTO request,
			HttpSession httpSession) {
		System.out.println("in user login " + request);
//		long id = labInchargeService.getPatientId(request.getEmail());
		try {
			LoginResponseDTO response = labInchargeService.login(request);
			httpSession.setAttribute("labIncharge_login_response", response);
			return ResponseEntity.ok(response);
		} catch (RuntimeException e) {
			System.out.println("err in add emp " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Credentials"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/profile")
	public ProfileDTO patientProfile(HttpSession httpSession) {
		System.out.println("in med incharge profile ");
		LoginResponseDTO labIncharge = (LoginResponseDTO) httpSession.getAttribute("labIncharge_login_response");
		System.out.println(labInchargeService.showProfile(labIncharge.getId()));
		return labInchargeService.showProfile(labIncharge.getId());
	}
	
	@PutMapping("/profile/changePassword/{id}")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO pcp ,@PathVariable long id){
		System.out.println("in  controller change password");
		try {
			labInchargeService.changePassword(id , pcp.getOldPassword(), pcp.getNewPassword());
			return new ResponseEntity<>(new ApiResponse("pasword changed successfully"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ApiResponse("Invalid old password"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/signout")
	public ResponseEntity<?> signout(HttpSession httpSession) {
		LoginResponseDTO labIncharge = (LoginResponseDTO) httpSession.getAttribute("labIncharge_login_response");
		httpSession.invalidate();
		return new ResponseEntity<>(new ApiResponse(labIncharge.getName() +" Logged out Successfully"), HttpStatus.OK);
	}
	
	@PostMapping("/addLabTest")
	public ResponseEntity<?> addLabTest(@RequestBody @Valid LabTestsDTO labTestDTO){
		System.out.println("in add lab test");
		try {
			labInchargeService.addLabTest(labTestDTO);
			return new ResponseEntity<>(new ApiResponse("lab test added successfully"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ApiResponse("failed to add lab test"), HttpStatus.BAD_REQUEST);
		}
	}
	
//	@PutMapping("/updateQty/{id}")
//	public ResponseEntity<?> updateQty(@PathVariable long id , @RequestBody MedQtyUpdateDTO medQtyDTO){
//		return new ResponseEntity<>(new ApiResponse(labInchargeService.updateQty(id , medQtyDTO)), HttpStatus.OK);
//	}
	
	@DeleteMapping("/deleteLabTest/{id}") //update quantity
	public ResponseEntity<?> deleteLabTest(@PathVariable long id){
		System.out.println(id);
		return new ResponseEntity<>(new ApiResponse(labInchargeService.deleteLabTest(id)), HttpStatus.OK);
	}
	
}
