package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

//	public Like selectLikeByUserIdPostId(
//			@Param("userId") int userId,
//			@Param("postId")int postId);
	
//	public int selectLikeCountByUserIdPostId(
//			@Param("userId") int userId,
//			@Param("postId")int postId);
	
//	public Boolean selectHeartByUserIdPostId(
//			@Param("userId") Integer userId,
//			@Param("postId")int postId);
	
	public void deleteLike(
			@Param("userId") int userId,
			@Param("postId")int postId);
	
	public void insertLike(
			@Param("userId") int userId,
			@Param("postId")int postId);
	
	
//	public int likeCount(int postId);
	
	//카운트 쿼리를 하나로 합친다.
	public int selectLikeCountByPostIdOrUserId(
			@Param("userId") Integer userId,
			@Param("postId")int postId);
	
	public void deleteLikeByPostId(
			@Param("postId")int postId);
}
