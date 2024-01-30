package com.enterprise.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.enterprise.controller.CustomPage;
import com.enterprise.dao.Column;
import com.enterprise.dao.CustomSpecification;
import com.enterprise.dao.Order;
import com.enterprise.dao.PagingRequest;
import com.enterprise.dao.UserVos;
import com.enterprise.dao.Uservo;
import com.enterprise.model.Coutry;
import com.enterprise.model.User;
import com.enterprise.repository.UserRepo;
import com.enterprise.service.ResponseHandler;
import com.enterprise.service.UserService;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ResponseHandler rh;
	
	/*
	 * @Autowired private UserRepository dataTableRepo;
	 */

	@Override
	public boolean addUser(Uservo uservo) {
		
		User user=new User();
		
		user.setUsername(uservo.getUsername());
		user.setPassword(uservo.getPassword());
		user.setName(uservo.getName());
		user.setEmail(uservo.getEmail());
		user.setMobile(uservo.getMobile());
		
		userRepo.save(user);
		return true;
		
	}

	@Override
	public List<User> getAllrecord(String name, String mobile, String username, String email) {
		// TODO Auto-generated method stub
		List<User> user=userRepo.findByNameContainsAndMobileContainsAndUsernameContainsAndEmailContains(name,mobile,username,email);
		//List<User> user=userRepo.findAll();
		return user;
	}

	@Override
	public  User getUserById(Integer userId) {

		Optional<User> user=userRepo.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Override
	public boolean updateUser(Uservo formData, Integer user_id) {
		// TODO Auto-generated method stub
		Optional<User> user=userRepo.findById(user_id);
		
		if(!user.isPresent()) {
			return false;
		}
		user.get().setEmail(formData.getEmail());
		user.get().setId(user_id);
		user.get().setMobile(formData.getMobile());
		user.get().setName(formData.getName());
		//userInfo.setUsername(formData.getUsername());
		user.get().setPassword(formData.getPassword());
		userRepo.save(user.get());
		return true;
	}

	@Override
	public CustomPage<UserVos> getAllUserInfo(PagingRequest input) {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------------"+input);
		Order order=input.getOrder().get(0);
		ArrayList<Column> column=input.getColumns();
		int searchIndex=order.getColumn();
		String direction=order.getDir();
		String orderProperty=column.get(searchIndex).getData();
		
		Pageable pageable=PageRequest.of(input.getStart()/input.getLength(), input.getLength(),Sort.Direction.fromString(direction),orderProperty);
		//Page page=userRepo.findAll(new CustomSpecification(input),pageable);
		Page page=userRepo.findAll( searchUserFilter(input),pageable);
		CustomPage c_page=new CustomPage();
		c_page.setData(page.getContent());
		c_page.setDraw(input.getDraw());
		c_page.setRecordsFiltered(page.getTotalElements());
		c_page.setRecordsTotal(page.getTotalElements());
		return c_page;
	}

	@Override
	public ResponseEntity<?> deleteUserById(Integer id) {
		Optional<User> user=userRepo.findById(id);
		if(user.isPresent()) {
			userRepo.deleteById(id);	
			return rh.response("Data is deleted", HttpStatus.OK);
		}else {
			return rh.response("Data is already deleted", HttpStatus.OK);
		}
	}

	/*
	 * @Override public DataTablesOutput<User> getAllUserInfo(@Valid DataTablesInput
	 * input) { // TODO Auto-generated method stub return
	 * dataTableRepo.findAll(input); }
	 */

	public static Specification<User> searchUserFilter(PagingRequest request) {
		return (root, query, criteriaBuilder) -> {
			
			List<Predicate> predicate=new ArrayList<Predicate>(); 
//			Join<User,Coutry> join=root.join("coutry");
			predicate.add(criteriaBuilder.like(root.get("name"),"%"+ request.getName()+"%"));
			predicate.add(criteriaBuilder.like(root.get("email"),"%"+ request.getEmail()+"%"));
			if(request.getDate()!=null) {
				System.out.println("--------------------"+request.getDate());
				predicate.add(criteriaBuilder.greaterThan(root.get("createdAt"), request.getDate().atStartOfDay().plusDays(1)));
			}
			if(request.getUserid()!=0) {
				predicate.add(criteriaBuilder.between(root.get("id"), request.getUserid(), 15));
			}
			
			//predicate.add(criteriaBuilder.like(root.get("coutry").get("name"),"%" +request.getCoutry()+"%"));
			return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
		};
	}

	@Override
	public boolean checkEmailExists(String username) {
		// TODO Auto-generated method stub
		Optional<User> user=userRepo.findByUsername(username);
		if(user.isPresent()) {
			return true;
		}
		return false;
	}

}
