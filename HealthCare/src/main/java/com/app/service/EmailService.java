package com.app.service;

import com.app.dto.EmailDetails;

public interface EmailService {
		 String sendSimpleMail(EmailDetails details);
		 
	
		    // Method
	
		    // To send an email with attachment
	
		    String sendMailWithAttachment(EmailDetails details);
}
