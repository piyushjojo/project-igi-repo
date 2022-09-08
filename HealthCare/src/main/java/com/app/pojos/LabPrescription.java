package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lab_prescription")
public class LabPrescription extends BaseEntity {
	

	@ManyToOne
	@JoinColumn(name = "appointment_id" , nullable = false )
	private Appointments appointment ;
	
	
	@ManyToOne
	@JoinColumn(name = "labtest_id" , nullable = false)
	private LabTests lab_test_id;
}
