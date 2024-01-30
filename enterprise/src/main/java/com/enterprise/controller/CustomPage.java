package com.enterprise.controller;

import java.util.List;

import lombok.Data;

@Data
public class CustomPage<T> {
	private List<T> data;
    private Long recordsFiltered;
    private Long recordsTotal;
    private int draw;
    private String error;
	
	

}
