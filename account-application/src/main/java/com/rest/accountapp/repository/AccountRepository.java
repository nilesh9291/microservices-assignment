package com.rest.accountapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.accountapp.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {	
	@Query("SELECT count(*) FROM Account acc where acc.type = :type AND acc.userId = :userId")
	public int findByTypeAndUserId(@Param("type") String type,@Param("userId") Long userId);
	
	@Query("SELECT acc FROM Account acc where acc.userId = :userId")
    public List<Account> findByUserId(@Param("userId") Long userId);
}
