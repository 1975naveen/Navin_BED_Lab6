package com.enterprise.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.enterprise.config.ClassDetail;
import com.enterprise.dao.Uservo;
import com.enterprise.model.User;
import com.enterprise.repository.UserRepo;
import com.enterprise.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WebController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	HttpSession session;
	
	@Autowired
    UserRepo userRepo;
	
	@GetMapping({"/login","/"})
	public ModelAndView login() {
		ModelAndView model =new ModelAndView();
		model.setViewName("login");
		return model;
	}
	@GetMapping("/home")
	public ModelAndView home() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role=auth.getAuthorities().parallelStream().toList().get(0).toString();
		if(role.equals("ADMIN")) {
			return new ModelAndView("redirect:/list-user?name=&mobile=&username=&email");
		}
		else if(role.equals("USER")){
			ClassDetail userDetails = (ClassDetail) auth.getPrincipal();
			Optional<User> user = userRepo.findByUsername(userDetails.getUsername());
			return new ModelAndView("redirect:/edit/"+user.get().getId());
		}else {
			return new ModelAndView("redirect:/login");
		}
		/*
		 * ModelAndView model =new ModelAndView(); model.setViewName("home"); return
		 * model;
		 */
	}
	@GetMapping("/list-user")
	public ModelAndView list_student(@RequestParam Optional<String> name,Optional<String> mobile,Optional<String> username,Optional<String> email) {
		/*
		 * String word="Hello.php"; String[] arrOfStr = word.split(".");
		 * 
		 * System.out.println(arrOfStr[0]);
		 */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ClassDetail userDetails = (ClassDetail) auth.getPrincipal();
		System.out.println("-----credential----------"+userDetails.getUsername());
		List<User> user= userservice.getAllrecord(name.get(),mobile.get(),username.get(),email.get()); 
		System.out.println(user);
		ModelAndView model =new ModelAndView();
		model.addObject("users", user);
		model.setViewName("list");
		return model;
	}
	@GetMapping("/add-user")
	public ModelAndView add_user() {
		ModelAndView model =new ModelAndView();
		model.addObject("message",session.getAttribute("message") );
		model.addObject("error",session.getAttribute("error") );
		session.removeAttribute("message");
		session.removeAttribute("error");
		model.setViewName("add_user");
		return model;
	}
	@GetMapping("/edit/{user_id}")
	public ModelAndView edit_user(@PathVariable Integer user_id) {
		System.out.println("--------------------------------------------"+user_id);
		User user= userservice.getUserById(user_id);
		ModelAndView model =new ModelAndView();
		model.addObject("message",session.getAttribute("message") );
		model.addObject("error",session.getAttribute("error") );
		session.removeAttribute("message");
		session.removeAttribute("error");
		model.addObject("user", user);
		model.setViewName("edit_user");
		return model;
	}
	@PostMapping("/edit/{user_id}")
	public String update_user(@ModelAttribute Uservo formData,@PathVariable Integer user_id ) {
		 System.out.println(formData);
		
		//return formData.getMobile();
		
		 	
		  boolean userstatus=userservice.updateUser(formData,user_id); 
		  if(userstatus) {
		  
		  }		  
		  session.setAttribute("message", "Data update successfully");
		  return "redirect:/edit/"+user_id;
		 
	}
	@GetMapping("/edit")
	public ModelAndView edit_user_detail() {
		Integer user_id=2;
		System.out.println("--------------------------------------------"+user_id);
		User user= userservice.getUserById(user_id);
		ModelAndView model =new ModelAndView();
		model.addObject("user", user);
		model.setViewName("edit_user");
		return model;
	}
	
	@PostMapping("/add-user")
	public String save_user(@ModelAttribute Uservo formData,HttpSession session ) {
		 System.out.println(formData);
		 boolean useremail=userservice.checkEmailExists(formData.getUsername());
		if(useremail) {
			session.setAttribute("error", "Username is already taken");
			  return "redirect:/add-user";
		}
		//return formData.getMobile();
			
		
		  boolean userstatus=userservice.addUser(formData); 
		  if(userstatus) {
		  
		  }		  
		  session.setAttribute("message", "Data save successfully");
		  return "redirect:/add-user";
		 
	}
}
