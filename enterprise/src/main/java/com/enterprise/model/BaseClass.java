/**
 * 
 */
package com.enterprise.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

/**
 * @author jay.suryawanshi
 *
 */

@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@MappedSuperclass
public class BaseClass {

    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;
    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt;
    @PrePersist
    public void prePersist() {
    	createdAt = LocalDateTime.now();
    	updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
    	updatedAt = LocalDateTime.now();
    }

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	} 
}
