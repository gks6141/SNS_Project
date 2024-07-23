package com.sns.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sns.comment.domain.Comment;

@Mapper
public interface CommentMapper {
	
	public void insertComment(
			@Param("userId") int userId, 
			@Param("postId") int postId, 
			@Param("content") String content);
	
	public List<Comment> selectCommentByPostId(int postId);
	
	public void deleteComment(int id);
	
	
	
}
