package com.blaze.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCreatePojo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String firstName, lastName, email, phone;	
	
}
