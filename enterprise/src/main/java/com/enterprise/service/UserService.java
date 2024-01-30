package com.enterprise.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.enterprise.controller.CustomPage;
import com.enterprise.dao.PagingRequest;
import com.enterprise.dao.UserVos;
import com.enterprise.dao.Uservo;
import com.enterprise.model.User;



public interface UserService {
	
	public boolean addUser(Uservo formData);

	public List<User> getAllrecord(String string, String string2, String string3, String string4);

	public User getUserById(Integer userId);

	public boolean updateUser(Uservo formData, Integer user_id);

	public CustomPage<UserVos> getAllUserInfo(PagingRequest input);

	public ResponseEntity<?> deleteUserById(Integer id);

	public boolean checkEmailExists(String username);

//	public DataTablesOutput<User> getAllUserInfo(@Valid DataTablesInput input);


	

}
