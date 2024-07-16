package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {
	
	@Autowired
	private PostRepository postRepository;
	
	public PostEntity addPostEntityByUserIdContentImagePath(int userId,String content,String imagePath) {
		return postRepository.save(PostEntity.builder()
									.userId(userId)
									.content(content)
									.imagePath(imagePath)
									.build());
	}
	public List<PostEntity> getPostEntityList(){
		
		return postRepository.findByOrderByIdDesc();
	}
}
