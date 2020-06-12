package com.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.UserServiceException;
import com.app.model.User;
import com.app.model.UserRequestDetail;
import com.app.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	IUserService userService;

	Map<String, User> users = new HashMap<>();

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getUser(@RequestParam String userId,
			@RequestParam(required = false, defaultValue = "9") int Page,
			@RequestParam(required = false, defaultValue = "9") Integer limit) {
		if (true)
			throw new UserServiceException("I am custom defined Exception");

		if (userId != null) {
			User user = users.get(userId);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/{id}")
	public String getUser(@PathVariable String id, @RequestParam(required = false, defaultValue = "90") int Page,
			@RequestParam(required = false, defaultValue = "90") int limit) {
		return "I am get Method with Id " + id;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User createUser(@Valid @RequestBody UserRequestDetail userRequestDetail) {
		User user = userService.createUser(userRequestDetail);
		return user;
	}

	@PutMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@PathVariable String userId,
			@RequestBody UserRequestDetail userRequestDetail) {
		User user = new User();
		user.setFirstName(userRequestDetail.getFname());
		user.setEmailId(userRequestDetail.getEmail());
		user.setUserId(userId);
		users.put(user.getUserId(), user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId) {
		if (users != null) {
			users.remove(userId);
			return new ResponseEntity<>(users, HttpStatus.OK);
		} else
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);

	}

}
