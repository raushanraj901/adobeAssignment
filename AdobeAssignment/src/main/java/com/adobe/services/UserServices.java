package com.adobe.services;

import java.util.List;

import com.adobe.dto.UserDto;
import com.adobe.dto.UserRespDto;
import com.adobe.model.User;

public interface UserServices {
	
	public UserRespDto registerUser(UserDto userDto);
	public UserRespDto deleteUserById(Long id);
	public UserRespDto findUserById(Long id);
	public UserRespDto updateUserDetails(Long id,UserDto upadteUserDto);
	public List<UserRespDto> getAllUsers();
	public List<UserRespDto> getTopActiveUser();
	public Long getTotalUserCount();
	
}
