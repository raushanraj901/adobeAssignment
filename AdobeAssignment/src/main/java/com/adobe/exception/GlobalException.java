package com.adobe.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.adobe.dto.RespModel;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<RespModel> responseNotFountException(ResourceNotFoundException rs){
		
		RespModel responseModel = new RespModel(rs.getMessage(), false, LocalDateTime.now());
		return new ResponseEntity<RespModel>(responseModel, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException rs){
		
		Map<String, String>  error = new HashMap<>();
		 rs.getBindingResult().getAllErrors().forEach((err)->{
			 String fieldName = ((FieldError) err).getField();
			 String massage = err.getDefaultMessage();
			 error.put(fieldName, massage);
		 });
		
		return new ResponseEntity<Map<String, String>>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<RespModel> dataIntegrityViolationException(DataIntegrityViolationException rs){
		String msg="data is already store in database!!                                ."
				+rs.getMostSpecificCause().toString();
		RespModel responseModel = new RespModel(msg, false, LocalDateTime.now());
		return new ResponseEntity<RespModel>(responseModel, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public ResponseEntity<RespModel> invalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException rs){
		RespModel responseModel = new RespModel(rs.getMessage(), false, LocalDateTime.now());
		return new ResponseEntity<RespModel>(responseModel, HttpStatus.BAD_REQUEST);
	}
}
