package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping("/sign-in-view")
	public String singInView() {
		return "user/signIn";
	}
	
	@GetMapping("/sign-up-view")
	public String singUpView() {
		return "user/signUp";
	}
}
