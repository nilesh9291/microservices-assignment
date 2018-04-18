package com.rest.userapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.userapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUserName(String userName);	
	
}
