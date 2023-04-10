package com.adobe.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespModel {
	
	private String message;
	private boolean status;
	private LocalDateTime DateTime;

}
