package com.rest.userapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.userapp.constants.UserApplicationConstants;
import com.rest.userapp.dto.UserDto;
import com.rest.userapp.entity.User;
import com.rest.userapp.exception.UserAlreadyExistsException;
import com.rest.userapp.exception.UserNotFoundException;
import com.rest.userapp.mapper.UserMapper;
import com.rest.userapp.repository.UserRepository;
import com.rest.userapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
		
	@Transactional
	public UserDto save(UserDto userDto) {		
		if (userRepository.findByUserName(userDto.getUserName()) != null) {
			 throw new UserAlreadyExistsException(UserApplicationConstants.USER_NAME_EXISTS);
		}		
		
		return userMapper.convertEntityToDto(userRepository.save(userMapper.convertDtoToEntity(userDto)));
	}

	public UserDto update(UserDto userDto) {
		return userMapper.convertEntityToDto(userRepository.save(userMapper.convertDtoToEntity(userDto)));
	}
	
	public UserDto findById(long userId) {
		User user =  userRepository.findOne(userId);
		
		if (user == null) {
            throw new UserNotFoundException(UserApplicationConstants.NOT_FOUND_404);
        }
		
		return userMapper.convertEntityToDto(user);
	}
	
	public List<UserDto> findAll() {
		List<User> userList = userRepository.findAll();
				
		if(userList == null || userList.size() == 0) {
			throw new UserNotFoundException(UserApplicationConstants.NOT_FOUND_404);
		}
						
		return userList.stream()
				.map(user -> userMapper.convertEntityToDto(user))
				.collect(Collectors.toList());
	}
			
	public void delete(long userId) {
		User user =  userRepository.findOne(userId);
		
		if (user == null) {
            throw new UserNotFoundException(UserApplicationConstants.NOT_FOUND_404);
        }
		userRepository.delete(userId);
	}
	
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	public UserDto findByUserName(String name) {		
		return userMapper.convertEntityToDto(userRepository.findByUserName(name));
	}
}
