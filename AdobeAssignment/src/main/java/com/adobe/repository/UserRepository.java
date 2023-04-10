package com.adobe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adobe.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u LEFT JOIN Post p GROUP BY u.id ORDER BY COUNT(p) ASC LIMIT 5")
	List<User> getTopUser();
	@Query("SELECT count(*) from USER")
	Long getAllUserCount();
}
