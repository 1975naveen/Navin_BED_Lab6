// Generated with g9.

package com.enterprise.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data

@Entity(name="employee")
public class Employee implements Serializable {
	@Id
    @Column(nullable=false, precision=11)
    private int id;
    @Column(name="first_name", nullable=false, length=50)
    private String firstName;
    @Column(name="last_name", nullable=false, length=50)
    private String lastName;
    @Column(nullable=false, precision=11)
    private int age;
    @Column(nullable=false)
    private LocalDateTime dob;

    /** Default constructor. */
    public Employee() {
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
     * Access method for firstName.
     *
     * @return the current value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method for firstName.
     *
     * @param aFirstName the new value for firstName
     */
    public void setFirstName(String aFirstName) {
        firstName = aFirstName;
    }

    /**
     * Access method for lastName.
     *
     * @return the current value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method for lastName.
     *
     * @param aLastName the new value for lastName
     */
    public void setLastName(String aLastName) {
        lastName = aLastName;
    }

    /**
     * Access method for age.
     *
     * @return the current value of age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter method for age.
     *
     * @param aAge the new value for age
     */
    public void setAge(int aAge) {
        age = aAge;
    }

    /**
     * Access method for dob.
     *
     * @return the current value of dob
     */
    public LocalDateTime getDob() {
        return dob;
    }

    /**
     * Setter method for dob.
     *
     * @param aDob the new value for dob
     */
    public void setDob(LocalDateTime aDob) {
        dob = aDob;
    }

}
