package com.enterprise.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.enterprise.model.Coutry;
import com.enterprise.model.User;
import com.enterprise.repository.StateRepo;
import com.enterprise.repository.UserRepo;


@Service
public class UserServiceDetails implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

	/*
	 * @Autowired AuthenticationManager authenticationManager;
	 */
   

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
         ClassDetail userdetails = new ClassDetail();
         Optional<User> user = userRepo.findByUsername(username);
         System.out.println("----------user present----------------"+user.isPresent());
         if(user.isPresent()) {
        	 System.out.println("-----------------------------------------"+user.get().getName());
        	 userdetails.setUsername(user.get().getUsername());
             userdetails.setPassword(user.get().getPassword());
             userdetails.setAccountNonExpired(true);
             userdetails.setAccountNonLocked(true);
             userdetails.setCredentialsNonExpired(true);
             userdetails.setEnabled(true);
             String role;
             if(user.get().getRole()==1) {
            	 role="ADMIN";
             }else {
            	 role="USER";
             }
             userdetails.setAuthorities(getAuthorities(role));
         }
           
            

        
        return userdetails;
    }

    

    private Collection<GrantedAuthority> getAuthorities(String role) {
        Collection<GrantedAuthority> authorities = new ArrayList<>(1);
        authorities.add(new SimpleGrantedAuthority(role.toUpperCase()));
        return authorities;
    }

    

}
