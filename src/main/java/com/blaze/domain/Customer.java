package com.blaze.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
	
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	
	@CreatedDate
	private Date created_date;
	
	@LastModifiedBy
	private Date update_date;
}
