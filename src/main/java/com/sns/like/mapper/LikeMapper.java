package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.like.domain.Like;

@Mapper
public interface LikeMapper {

	public Like selectLikeByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId")int postId);
	
	public Boolean selectHeartByUserIdPostId(
			@Param("userId") Integer userId,
			@Param("postId")int postId);
	
	public void deleteLike(
			@Param("userId") int userId,
			@Param("postId")int postId);
	
	public void insertLike(
			@Param("userId") int userId,
			@Param("postId")int postId);
	
	
	public int likeCount(int postId);
	
}
