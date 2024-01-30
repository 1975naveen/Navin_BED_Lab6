package com.enterprise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.enterprise.dao.CustomSpecification;
import com.enterprise.model.User;

public interface UserRepo extends JpaRepository<User, Integer>,JpaSpecificationExecutor {

	List<User> findByNameContainsAndMobileContainsAndUsernameContainsAndEmailContains(String name, String mobile, String username, String email);

	Page findByNameContainsAndEmailStartsWith(String name, String email, Pageable pageable);

	Optional<User> findByName(String username);

	Optional<User> findByUsername(String username);


	//List<User> findByNameAndMobileAndUsernameAndEmail(String name, String mobile, String username, String email);
	
	/*
	 * select u.* FROM User u WHERE " + "u.name LIKE CONCAT('%',:name, '%')" +
	 * "or u.mobile LIKE CONCAT('%',:mobile, '%')" +
	 * " or u.username LIKE CONCAT('%',:username, '%')" + "Or p.email LIKE
	 * CONCAT('%', :email, '%')
	 */	
	/*
	 * @Query("select * FROM user") List<User> findAllUser(String name, String
	 * mobile, String username, String email);
	 */

}
