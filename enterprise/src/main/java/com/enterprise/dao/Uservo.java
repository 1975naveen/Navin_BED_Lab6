package com.enterprise.dao;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

@Data
public class Uservo {
	
	private int id;
	@NonNull()
	private String username;
	@NonNull()
	private String password;
	@NonNull()
	private String email;
	@NonNull()
	private String name;
	@NonNull()
	private String mobile;	
}
