package com.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
	//order history 	-> directly fetch order medicine or lab order
	//buy meds 		-> buy , cancel order 
	//									\--> prescription : array{name , qty , price}
	//book a lab test -> book , cancel , reschedule
	//									\--> date , time , test-name (array format)
}
