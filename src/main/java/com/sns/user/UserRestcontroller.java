package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestcontroller {
	
	@Autowired
	private UserBO userBO;
	
	
	@PostMapping("/login")
	public Map<String, Object> userLogin(
			@RequestParam("loginId") String LoginId,
			@RequestParam("password") String Password,
			HttpServletRequest request){
		
		String hashedpassword= EncryptUtils.md5(Password);
		
		UserEntity user = userBO.getUserEntityByLoginIdPassword(LoginId, hashedpassword);
		
		Map<String, Object> result = new HashMap<>();
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("UserId", user.getId());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userLoginId", user.getLoginId());
			
			
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 403);
			result.put("error_message","존재하지 않는 사용자입니다.");
		}
		return result;
	}
	
	
	@PostMapping("/signUp")
	public Map<String, Object> userSignUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email){
		
		
		String hashedPassword = EncryptUtils.md5(password);
		
		UserEntity user = userBO.addUser(loginId, hashedPassword, name, email);
		
		Map<String, Object> result = new HashMap<>();
		if(user != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "추가되지 않음");
		}
		return result;
		
	}
	
	@RequestMapping("/is-duplicated-id")
	public Map<String,Object> isDuplicatedId(
			@RequestParam("loginId") String loginId){
		
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		
		Map<String,Object> result = new HashMap<>();
		result.put("code", 200);
		if(user != null) {
			result.put("is_duplicated_id", true);
		} else {
			result.put("is_duplicated_id", false);
		}
		return result;
		
	}
	
}
