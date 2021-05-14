package com.yellowdogprod.rootsandbones.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yellowdogprod.rootsandbones.ResponseBean;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/login")
@AllArgsConstructor
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseBean<User> login(@RequestBody LoginRequest request, HttpSession session){
		ResponseBean<User> user = userService.login(request.getUsername(), request.getPassword());
		session.setAttribute("userId", user.getResult().getId());
		return user;
	}

}

