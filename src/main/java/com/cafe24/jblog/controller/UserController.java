package com.cafe24.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
		
	@RequestMapping("/gologin")
	public String goLogin() {
		return "user/login";
	}
	@RequestMapping("/gosignup")
	public String goSignUp() {
		return "user/signup";
	}
	@RequestMapping("signup")
	public String doSignUp(@ModelAttribute UserVo vo) {

		userService.signup(vo);
		return "user/signupsuccess";
	}

	
	

}
