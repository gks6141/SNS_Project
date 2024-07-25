package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/timeline/post")
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String,Object> create(HttpSession session,
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file) {
		
		Map<String,Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		if(userId == null && userLoginId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인 상태가 아닙니다.");
		}
		PostEntity post= postBO.addPostEntityByUserIdContentImagePath(userId, userLoginId, content, file);
		
		
		
		if(post != null) {
			result.put("code", 200);
			result.put("result", "성공");			
		} else {
			result.put("code", 500);
			result.put("error_message", "DB에 입력 실패");	
		}
		
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId
			,HttpSession session){
		
		int userId = (int)session.getAttribute("userId");
		postBO.deletePost(userId, postId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
		
		
	}
	
}
