package com.app.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
 
// Class

public class EmailDetails {
 

    // Class data members

    private String recipient;

    private String msgBody;

    private String subject;

    private String attachment;

	public EmailDetails(String recipient, String msgBody, String subject) {
		super();
		this.recipient = recipient;
		this.msgBody = msgBody;
		this.subject = subject;
	}
    
    
}
