package com.adobe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.dto.UserDto;
import com.adobe.dto.UserRespDto;
import com.adobe.exception.UserException;
import com.adobe.model.User;
import com.adobe.services.UserServices;

@RestController
public class UserController {
	@Autowired(required = true)
	private UserServices userServices;
	
	@PostMapping("/users")
	public ResponseEntity<UserRespDto> registerUser(@RequestBody UserDto user) {
		
		UserRespDto saveUser = userServices.registerUser(user);
		return new ResponseEntity<UserRespDto>(saveUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserRespDto> findUserById(@PathVariable("id") Long id){
		UserRespDto user = userServices.findUserById(id);
		return new ResponseEntity<UserRespDto>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<UserRespDto> deleteUserById(@PathVariable("id") Long id){
		UserRespDto deletedUser = userServices.deleteUserById(id);
		return new ResponseEntity<UserRespDto>(deletedUser,HttpStatus.OK);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<UserRespDto> updateUserDetails(@PathVariable("id") Long id,@RequestBody UserDto updatedUser){
		
		UserRespDto user = userServices.updateUserDetails(id, updatedUser);
		
		return new ResponseEntity<UserRespDto>(user,HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserRespDto>> getAllUser(){
		
		List<UserRespDto> list = userServices.getAllUsers();
		
		return new ResponseEntity<List<UserRespDto>>(list,HttpStatus.OK);
	}
	@GetMapping("/analytics/users/top-active")
	public ResponseEntity<List<UserRespDto>> getTopUsers() {
		
		List<UserRespDto> list = userServices.getTopActiveUser();
		
		return new ResponseEntity<List<UserRespDto>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/analytics/users")
    public ResponseEntity<Long> getTotalNumberOfUser(){
    	return new ResponseEntity<Long>(userServices.getTotalUserCount(), HttpStatus.OK);
    }
    
	
}
