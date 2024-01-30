package com.enterprise.service;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class ResponseHandler {
	public ResponseEntity<?> response(String message,HttpStatus status,Object data){
		HashMap<String,Object> response=new HashMap<String,Object>();
		response.put("message", message);
		response.put("status", status.value());
		if(HttpStatus.OK.equals(status)) {
			response.put("data", data);
		}
		else {
			response.put("error", data);
		}
		
		
		return new ResponseEntity<HashMap<String,Object>>(response,status);
	}
	public ResponseEntity<?> response(String message,HttpStatus status){
		HashMap<String,Object> response=new HashMap<String,Object>();
		response.put("message", message);
		response.put("status", status.value());
		
		return new ResponseEntity<HashMap<String,Object>>(response,status);
	}
}