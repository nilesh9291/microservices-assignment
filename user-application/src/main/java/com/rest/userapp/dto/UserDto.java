package com.rest.userapp.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * This dto class act as a data transfer object between the controller and the data layer.  
 * @author ii
 *
 */

public class UserDto implements Serializable {
    
	private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "The database generated user ID")
    private Long userId;
	
	@ApiModelProperty(notes = "The unique username of the user.")	
	@NotEmpty(message = "User Name can not be empty.")
	@Size(min=2, max=20, message = "User Name must be between 2 to 20 characters.")
	private String userName;
	
    @ApiModelProperty(notes = "User's first name")
    @NotEmpty(message = "First Name can not be empty.")
    @Size(min=2, max=20, message = "First Name must be between 2 to 20 characters.")
	private String firstName;

    @ApiModelProperty(notes = "User's last name")
    @NotEmpty(message = "Last Name can not be empty.")
    @Size(min=2, max=20, message = "Last Name must be between 2 to 20 characters.")
	private String lastName;
    	
    @ApiModelProperty(notes = "User's age")
    @Min(value = 18, message="User's age must be above 18.")
    private int age;
    
	public UserDto() {
		
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public UserDto(Long userId, String userName, String firstName, String lastName, int age) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", age=" + age + "]";
	}		
}
