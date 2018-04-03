package com.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	//public Account findByType(String type);
	
	@Query("SELECT count(*) FROM Account acc where acc.type = :type AND acc.user.id = :userId")
	public int findByTypeAndUserId(@Param("type") String type,@Param("userId") long userId);
    //public Optional<Long> findByTypeAndUserId(@Param("type") String type,@Param("userId") long userId);
	
	@Query("SELECT acc FROM Account acc where acc.user.id = :userId")
    public List<Account> findByUserId(@Param("userId") long userId);
}
