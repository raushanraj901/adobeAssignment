package com.adobe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adobe.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	@Query("SELECT COUNT(*) FROM Post")
	Long getAllPost(); 
	
	@Query("SELECT P FROM Post P ORDER BY LIKES DESC LIMIT 5")
	List<Post> getTopPost();
}
