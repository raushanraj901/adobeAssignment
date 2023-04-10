package com.adobe.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRespDto {
	
	private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    

    @Column(name = "created_date")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_date")
    private LocalDateTime updatedAt;
}
