package com.app.service;

import com.app.dto.EmailDetails;

public interface EmailService {
	String sendSimpleMail(EmailDetails details);

	String sendMailWithAttachment(EmailDetails details);
}
