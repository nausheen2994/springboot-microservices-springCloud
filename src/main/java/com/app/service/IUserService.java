package com.app.service;

import com.app.model.User;
import com.app.model.UserRequestDetail;

public interface IUserService {
	
	public User createUser(UserRequestDetail userRequestDetail);

}
