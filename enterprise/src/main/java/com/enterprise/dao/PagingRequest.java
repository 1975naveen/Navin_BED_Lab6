package com.enterprise.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import lombok.Data;

@Data
public class PagingRequest {
	public int draw;
	public ArrayList<Column> columns;
	public ArrayList<Order> order;
	public int start;
	public int length;
	public Search search;
	
	
	  private String name;
	  private String coutry;
	  private String email;
	  private int userid;
	  private LocalDate date;
	  //private Integer status;
	 
}
