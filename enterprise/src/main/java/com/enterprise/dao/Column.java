package com.enterprise.dao;

import lombok.Data;

@Data
public class Column {
	public String data;
    public String name;
    public boolean searchable;
    public boolean orderable;
    public Search search;
}
