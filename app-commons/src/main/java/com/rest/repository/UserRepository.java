package com.rest.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.entity.Account;
import com.rest.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUserName(String userName);
	
	@Query("SELECT acc FROM Account acc where acc.user.id = :userId")
    public Set<Account> findByUserId(@Param("userId") long userId);
}
