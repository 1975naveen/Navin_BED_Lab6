package com.enterprise.dao;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class UserVos {
    private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String mobile;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
