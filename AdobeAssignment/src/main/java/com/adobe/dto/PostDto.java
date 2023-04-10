package com.adobe.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PostDto {
	
	private Long id;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Content cannot be null")
    @Size(min = 1, max = 300, message = "Content must be between 1 and 300 characters")
    private String content;
    
    private Integer likes = 0;

}
