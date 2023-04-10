package com.adobe.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.dto.PostDto;
import com.adobe.dto.UserRespDto;
import com.adobe.exception.ResourceNotFoundException;
import com.adobe.model.Post;
import com.adobe.repository.PostRepository;
import com.adobe.repository.UserRepository;

@Service
public class PostServiceImp implements PostServices{
	
	@Autowired(required = true)
	private PostRepository pRepo;
	
	@Autowired(required = true)
	private UserServices userService;
	
	@Autowired
	private ModelMapper mMapper;

	@Override
	public PostDto createPost(PostDto postDto) {
		UserRespDto userDto = userService.findUserById(postDto.getUserId());
		Post post = mMapper.map(postDto, Post.class);

		Post sPost = pRepo.save(post);
		return mMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto deletePostById(Long id) {
		Post post = pRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
		pRepo.delete(post);
		return mMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto findPostById(Long id) {
		Post post = pRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
		return mMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePostDetails(Long id, PostDto postDto) {
		Post post = pRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
		post.setContent(postDto.getContent());
		Post updatedPost = pRepo.save(post);
		return mMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostDto likesPost(Long id) {
		Post post = pRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
		post.setLikes(post.getLikes() + 1);
		Post likedPost = pRepo.save(post);
		return mMapper.map(likedPost, PostDto.class);
	}

	@Override
	public PostDto disLikesPost(Long id) {
		Post post = pRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
		int likes = post.getLikes();
		post.setLikes(likes > 0 ? likes - 1 : 0);
		Post unlikedPost = pRepo.save(post);
		return  mMapper.map(unlikedPost, PostDto.class);
	}

	@Override
	public Long getTotalPosts() {
		return pRepo.getAllPost();
	}

	@Override
	public List<PostDto> getTopPosts() {
		List<Post> posts = pRepo.getTopPost();
		return posts.stream().map(post-> mMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<Post> posts = pRepo.findAll();
		return posts.stream().map(post-> mMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

	
}
