// Generated with g9.

package com.enterprise.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data

@Entity(name="state")
public class State implements Serializable {
	@Id
    @Column(nullable=false, precision=11)
    private int id;
    @Column(nullable=false, length=255)
    private String name;
    @Column(nullable=false, length=32)
    private String code;
    @Column(name="create_time")
    private LocalDateTime createTime;
    @Column(name="updated_time")
    private LocalDateTime updatedTime;

    /** Default constructor. */
    public State() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(int aId) {
        id = aId;
    }

    /**
     * Access method for name.
     *
     * @return the current value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name.
     *
     * @param aName the new value for name
     */
    public void setName(String aName) {
        name = aName;
    }

    /**
     * Access method for code.
     *
     * @return the current value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for code.
     *
     * @param aCode the new value for code
     */
    public void setCode(String aCode) {
        code = aCode;
    }

    /**
     * Access method for createTime.
     *
     * @return the current value of createTime
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * Setter method for createTime.
     *
     * @param aCreateTime the new value for createTime
     */
    public void setCreateTime(LocalDateTime aCreateTime) {
        createTime = aCreateTime;
    }

    /**
     * Access method for updatedTime.
     *
     * @return the current value of updatedTime
     */
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    /**
     * Setter method for updatedTime.
     *
     * @param aUpdatedTime the new value for updatedTime
     */
    public void setUpdatedTime(LocalDateTime aUpdatedTime) {
        updatedTime = aUpdatedTime;
    }

}
