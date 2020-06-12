package com.app.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.model.UserRequestDetail;
import com.app.util.Utils;

@Service
public class UserService implements IUserService {

	Utils utils;

	public UserService() {
	}

	@Autowired
	public UserService(Utils utils) {
		this.utils = utils;

	}

	Map<String, User> users = new HashMap<>();

	@Override
	public User createUser(UserRequestDetail userRequestDetail) {
		User user = new User();
		user.setEmailId(null);
		user.setFirstName(userRequestDetail.getFname());
		user.setUserId(utils.generateUserId());
		if (users == null)
			new HashMap<>();
		users.put(user.getUserId(), user);
		return user;

	}

}
