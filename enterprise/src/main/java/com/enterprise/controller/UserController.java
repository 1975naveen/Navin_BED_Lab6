package com.enterprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.dao.DeleteUser;
import com.enterprise.dao.PagingRequest;
import com.enterprise.dao.UserVos;
import com.enterprise.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/get-all-user")
	public CustomPage<UserVos> getAllUser(@RequestBody PagingRequest input) {
		System.out.println("----------------------------"+input);
		return userService.getAllUserInfo(input);
	}
	@GetMapping("/get-all-user")
	public String getAllUser() {
		return "jjjj";
	}
	@DeleteMapping("/delete-user-by-id")
	public ResponseEntity<?> deleteUserById(@RequestBody DeleteUser user) {
		System.out.println();
		return userService.deleteUserById(user.getId());
	}

}
