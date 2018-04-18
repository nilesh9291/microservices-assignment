package com.rest.aggregator.dto;

import java.util.Set;

public class UserDetailsDto {
	private Long userId;
	
	private String userName;
	
	private String firstName;

	private String lastName;
    
    private int age;
    
    private Set<AccountDetailsDto> accountDetailsDto;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Set<AccountDetailsDto> getAccountDetailsDto() {
		return accountDetailsDto;
	}

	public void setAccountDetailsDto(Set<AccountDetailsDto> accountDetailsDto) {
		this.accountDetailsDto = accountDetailsDto;
	}

	@Override
	public String toString() {
		return "User Details [userId=" + userId + ", userName=" + userName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", age=" + age + ", accountDetailsDto=" + accountDetailsDto + "]";
	}		
}
