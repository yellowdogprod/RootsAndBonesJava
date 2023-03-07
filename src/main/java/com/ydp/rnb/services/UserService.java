package com.ydp.rnb.services;

import org.springframework.stereotype.Service;

import com.ydp.rnb.views.UserView;

@Service
public class UserService {

	public UserView getUser(long id) {
		return new UserView(1L, "mario", "rossi");
	}
	
}
