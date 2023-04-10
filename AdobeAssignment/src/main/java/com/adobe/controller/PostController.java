package com.adobe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.dto.PostDto;
import com.adobe.exception.PostException;
import com.adobe.exception.UserException;
import com.adobe.model.Post;
import com.adobe.model.User;
import com.adobe.services.PostServices;

@RestController
public class PostController {
	
	@Autowired(required = true)
	private PostServices postServices;
	
	@PostMapping("/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto post) {
		
		PostDto savePost = postServices.createPost(post);
		return new ResponseEntity<PostDto>(savePost,HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDto> findPostById(@PathVariable("id") Long id) throws PostException{
		PostDto user = postServices.findPostById(id);
		return new ResponseEntity<PostDto>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<PostDto> deletePostById(@PathVariable("id") Long id) throws PostException{
		PostDto deletedUser = postServices.deletePostById(id);
		return new ResponseEntity<PostDto>(deletedUser,HttpStatus.OK);
	}
	
	@PutMapping("/posts/{id}")
	public ResponseEntity<PostDto> updatePostById(@PathVariable("id") Long id, @RequestBody PostDto postUpdateDto) throws PostException{
		
		PostDto updatedUser = postServices.updatePostDetails(id, postUpdateDto);
		
		return new ResponseEntity<PostDto>(updatedUser,HttpStatus.OK);
	}
	
	@PostMapping("/posts/{id}/like")
	public ResponseEntity<PostDto> likePost(@PathVariable("id") Long id) {
		
		PostDto likePost = postServices.likesPost(id);
		return new ResponseEntity<PostDto>(likePost,HttpStatus.CREATED);
	}
	
	@PostMapping("/posts/{id}/unlike")
	public ResponseEntity<PostDto> dislikePost(@PathVariable("id") Long id) {
		
		PostDto dislikePost = postServices.disLikesPost(id);
		return new ResponseEntity<PostDto>(dislikePost,HttpStatus.CREATED);
	}
	
	@GetMapping("/analytics/posts")
    public ResponseEntity<Long> getTotalPosts() {
        Long totalPosts = postServices.getTotalPosts();
        return ResponseEntity.ok(totalPosts);
    }

    @GetMapping("/analytics/posts/top-liked")
    public ResponseEntity<List<PostDto>> getTopLikedPosts() {
        List<PostDto> topLikedPosts = postServices.getTopPosts();
        return ResponseEntity.ok(topLikedPosts);
    }
    
    
    @GetMapping("/post")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> topLikedPosts = postServices.getAllPosts();
        return ResponseEntity.ok(topLikedPosts);
    }
	
}
