package com.selab.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	@GetMapping("/signIn")
	public void getSignIn() {
		
	}
	
	@GetMapping("/signOut")
	public void getSignOut() {
		
	}
	
	@GetMapping("/accessDenied")
	public void getAccessDenied() {
		
	}
	
	@PostMapping("/signInFail")
	public String postSignInFail(Model model) {
		String message = "로그인 실패";
		model.addAttribute("message", message);
		
		return "signIn";
	}

}
