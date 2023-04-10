package com.adobe.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.dto.UserDto;
import com.adobe.dto.UserRespDto;
import com.adobe.exception.ResourceNotFoundException;
import com.adobe.model.User;
import com.adobe.repository.UserRepository;

@Service
public class UserServiceImp implements UserServices{
	
	@Autowired(required = true)
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserRespDto registerUser(UserDto userDto){
		
		User user = modelMapper.map(userDto,User.class);
		user.setCreateDate(LocalDateTime.now());;
		user.setModiDate(LocalDateTime.now());
		
		User saveUser = userRepository.save(user);
		return modelMapper.map(saveUser, UserRespDto.class);
	}

	@Override
	public UserRespDto deleteUserById(Long id) {
		User user=userRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+id));
		userRepository.deleteById(id);
		return modelMapper.map(user, UserRespDto.class);
	}

	@Override
	public UserRespDto findUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+id));
		return modelMapper.map(user, UserRespDto.class);
	}

	@Override
	public UserRespDto updateUserDetails(Long id, UserDto upadteUserDto) {
		User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setFirstName(upadteUserDto.getFirstName());
        user.setLastName(upadteUserDto.getLastName());
        user.setBio(upadteUserDto.getBio());
        user.setModiDate(LocalDateTime.now());;
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserRespDto.class);
	}

	@Override
	public List<UserRespDto> getAllUsers() {
		List<User> users = userRepository.findAll();
        List<UserRespDto> userDtos = users.stream().map(user-> modelMapper.map(user, UserRespDto.class)).collect(Collectors.toList());
        return userDtos;
	}

	@Override
	public List<UserRespDto> getTopActiveUser() {
		List<User> users = userRepository.getTopUser();
        List<UserRespDto> userDtos = users.stream().map(user-> modelMapper.map(user, UserRespDto.class)).collect(Collectors.toList());
        return userDtos;
	}

	@Override
	public Long getTotalUserCount() {
		return userRepository.getAllUserCount();
	}

}
