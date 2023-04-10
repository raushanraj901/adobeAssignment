package com.adobe.services;

import java.util.List;

import com.adobe.dto.PostDto;
import com.adobe.exception.PostException;
import com.adobe.exception.UserException;
import com.adobe.model.Post;
import com.adobe.model.User;

public interface PostServices {
	public PostDto createPost(PostDto postDto);
	public PostDto deletePostById(Long id);
	public PostDto findPostById(Long id);
	public PostDto updatePostDetails(Long id, PostDto postDto);
	public PostDto likesPost(Long id);
	public PostDto disLikesPost(Long id);
	public Long getTotalPosts();
    public List<PostDto> getTopPosts();
    public List<PostDto> getAllPosts();
	
}
