package com.rest.userapp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.User;
import com.rest.exception.ResourceExistsException;
import com.rest.exception.ResourceNotFoundException;
import com.rest.repository.UserRepository;
import com.rest.userapp.service.UserService;
import com.rest.constant.ApplicationConstants;

@Service
public class UserServiceImpl implements UserService {	
	@Autowired
	private UserRepository userRepository;
	
	
	@Transactional
	public User save(User user) {		
		if (userRepository.findByUserName(user.getUserName()) != null) {
			 throw new ResourceExistsException(ApplicationConstants.USER_NAME_EXISTS);
		}
		
		return userRepository.save(user);
	}

	public User update(User user) {
		return userRepository.save(user);
	}
	
	public User findById(long id) {
		User user =  userRepository.findOne(id);
		
		if (user == null) {
            throw new ResourceNotFoundException(ApplicationConstants.NOT_FOUND_404);
        }
		
		return user;
	}
	
	public List<User> findAll() {
		List<User> userList = userRepository.findAll();
				
		if(userList == null || userList.size() == 0) {
			throw new ResourceNotFoundException(ApplicationConstants.NOT_FOUND_404);
		}
				
		return userList;
	}
			
	public void delete(long id) {
		User user =  userRepository.findOne(id);
		
		if (user == null) {
            throw new ResourceNotFoundException(ApplicationConstants.NOT_FOUND_404);
        }
		userRepository.delete(id);
	}
	
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	public User findByUserName(String name) {		
		return userRepository.findByUserName(name);
	}
}
