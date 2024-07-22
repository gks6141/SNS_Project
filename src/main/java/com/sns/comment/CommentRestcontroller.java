package com.sns.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.Comment;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/comment")
public class CommentRestcontroller {

	@Autowired
	private CommentBO commentBO;
	
	@RequestMapping("/create")
	public Map<String, Object> commentCreate(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session,
			Model model){
		
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String,Object> result= new HashMap<>();
		List<Comment> comments = commentBO.getCommentByPostId();
		
		model.addAttribute("comments", comments);
		
		if(userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요");
			return result;
		}
		
		commentBO.addComment(userId, postId, content);
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
		
		
	}
}
