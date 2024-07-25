package com.sns.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{
	public List<PostEntity> findByOrderByIdDesc();
	
	public void deleteByUserIdAndId(int userId, int postId);
	
	public PostEntity findByUserIdAndId(int userId,int postId);
}
