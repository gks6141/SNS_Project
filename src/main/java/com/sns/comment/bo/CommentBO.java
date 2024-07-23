package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentView;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class CommentBO {
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	public void addComment(int userId, int postId, String content) {
		commentMapper.insertComment(userId, postId, content);
	}
	
	//input: 글번호   output: List<CommentView>
	public List<CommentView> generateCommentViewListByPostId(int postId){
		List<CommentView> commentViewList = new ArrayList<>();
		//댓글들 가져옴
		List<Comment> commentList = commentMapper.selectCommentByPostId(postId);
		
		//반복문 순회 => comment -> commentVIew  =>List 에 담음
		for (Comment comment : commentList) {
			
			CommentView commentView = new CommentView(); 
			
			commentView.setComment(comment);
			
			UserEntity user = userBO.getUserEntityById(commentView.getComment().getUserId()); 
			
			commentView.setUser(user);
			
			commentViewList.add(commentView);
			
		}
		
		return commentViewList;
	}
	
	public void deleteComment(int id) {
		commentMapper.deleteComment(id);
	}
}
