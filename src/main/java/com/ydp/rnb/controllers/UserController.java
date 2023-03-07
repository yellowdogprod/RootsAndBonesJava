package com.ydp.rnb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydp.rnb.services.UserService;
import com.ydp.rnb.utils.UserViewUtils;
import com.ydp.rnb.views.UserView;

@RestController("/user/")
public class UserController {


	@Autowired
	private UserService userService;
	
	@GetMapping
	public UserView getUser(long userId) {
		UserView uv = userService.getUser(userId);
		System.out.println(UserViewUtils.enviousMethod());
		System.out.println(UserViewUtils.getCompleteName(uv));
		boolean isMario = UserViewUtils.checkIfMarioRossi(uv);
		
		if(isMario) {
			uv = UserViewUtils.getMarioSpecialFriend();
			System.out.println(UserViewUtils.getCompleteName(uv));
		}
		else {
			System.out.println(UserViewUtils.notMarioMessage());
		}
		
		return uv;
	}
	
}
