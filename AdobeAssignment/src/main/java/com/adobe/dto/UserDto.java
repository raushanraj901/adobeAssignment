package com.adobe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private Long id;
	
	@NotNull(message = "Name should not null")
	@Size(min = 1,max = 50,message = "Name length should be 1 to 50")
	private String firstName;
	private String lastName;
	
	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
	private String email;

	@Size(max = 200, message = "Bio length should be less or equal 200 characters")
	private String bio;

}
