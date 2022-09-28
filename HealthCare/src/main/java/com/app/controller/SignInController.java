package com.app.controller;

import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.config.TokenManager;
import com.app.dto.ApiResponse;
import com.app.dto.LoginRequestDTO;
import com.app.dto.LoginResponseDTO;
import com.app.dto.RequestValidateToken;
import com.app.pojos.MedicineIncharge;
import com.app.pojos.Patient;
import com.app.service.IPatientService;
import com.app.service.MedInchargeService;
import com.app.service.SignInService;

@RestController
@RequestMapping("/signin")
@CrossOrigin(origins = "http://localhost:3000")
public class SignInController {

	@Autowired
	private MedInchargeService medInchargeService;

	@Autowired
	private SignInService signinService;

	@Autowired
	private IPatientService patientService;

	@Autowired
	private AuthenticationConfiguration authenticationManager;

	@Autowired
	private TokenManager tokenManager;

	@PostMapping
	public ResponseEntity<?> createToken(@RequestBody LoginRequestDTO request) throws Exception {
		System.out.println("in user login " + request);

		try {
			System.out.println("in create token");
			authenticationManager.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					request.getEmail() + ":" + request.getUser(), request.getPassword()));
		} catch (DisabledException e) {

			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			System.out.println("in INVALID_CREDENTIALS ");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		final UserDetails userDetails = signinService.loadUserByUsername(request.getEmail() + ":" + request.getUser());
		final String jwtToken = tokenManager.generateJwtToken(userDetails);

		if (request.getUser().equals("PATIENT")) {
			Patient u = patientService.findByEmail(request.getEmail());
			if (!u.isStatus()) {
				return new ResponseEntity<>(new ApiResponse("Invalid Credentials"), HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity
					.ok(new LoginResponseDTO(jwtToken, u.getId(), u.getName(), u.getEmail(), u.getWallet()));
		} else {
			MedicineIncharge u = medInchargeService.login(request);

			return ResponseEntity.ok(new LoginResponseDTO(jwtToken, u.getId(), u.getName(), u.getEmail()));
		}

	}

	@PostMapping("/validate-token")
	public ResponseEntity<?> validateToken(@RequestBody RequestValidateToken tokenRequest) throws Exception {

		return ResponseEntity
				.ok(tokenManager.validateJwtToken(tokenRequest.getToken(), User.withUsername(tokenRequest.getUsername())
						.authorities(new ArrayList<SimpleGrantedAuthority>()).password("").build()));
	}

}
