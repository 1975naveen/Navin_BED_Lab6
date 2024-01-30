// Generated with g9.

package com.enterprise.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Table(name="user")
@Data
public class User extends BaseClass implements Serializable{
	@Id
    @Column(nullable=false, precision=11)
    private int id;
    @Column(nullable=false, length=200)
    private String username;
    @Column(nullable=false, length=50)
    private String password;
    @Column(nullable=false, length=70)
    private String name;
    @Column(nullable=false, length=70)
    private String email;
    @Column(nullable=false, length=15)
    private String mobile; 
    
    private int role;


}
