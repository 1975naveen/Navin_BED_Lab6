// Generated with g9.

package com.enterprise.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name="coutry")
public class Coutry implements Serializable {
	@Id
    @Column(nullable=false, precision=11)
    private int id;
    @Column(length=255)
    private String name;
    @Column(nullable=false, length=20)
    private String password;
    @Column(nullable=false, length=32)
    private String code;
    @Column(name="create_time")
    private LocalDateTime createTime;
    @Column(name="updated_time")
    private LocalDateTime updatedTime;
    

   

}
