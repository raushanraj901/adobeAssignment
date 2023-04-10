package com.adobe.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	private String bio;
	
	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	@Column(name = "updated_date")
	private LocalDateTime modiDate;
	
}
