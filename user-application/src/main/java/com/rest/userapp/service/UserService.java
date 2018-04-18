package com.rest.userapp.service;

import java.util.List;

import com.rest.userapp.dto.UserDto;

public interface UserService {
	UserDto save(UserDto userDto);
	
	UserDto update(UserDto userDto);

	UserDto findById(long id);

	List<UserDto> findAll();

	void delete(long userId);
	
	void deleteAll();

	UserDto findByUserName(String userName);
}
