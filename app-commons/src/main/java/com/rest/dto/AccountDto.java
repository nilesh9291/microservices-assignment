package com.rest.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AccountDto {   
   	private Long id;
        
    @NotEmpty(message = "Account type can not be empty.")
	@Size(min=2, max=15, message = "Account type must be between 2 to 15 characters.")
    private String type;
   
    //@NotEmpty(message = "Account number can not be empty.")
	//@Size(min=15, max=15, message = "Account type must be maximum 15 characters.")    
    //private String accountNumber;

    @NotEmpty(message = "Account Description can not be empty.")
	@Size(min=2, max=50, message = "Account Description must be between 2 to 100 characters.")
    private String description;

    private long userId;
    
    public AccountDto() {
    	
    }

	public AccountDto(String type, String description, long userId) {
		super();
		this.type = type;
		//this.accountNumber = accountNumber;
		this.description = description;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}*/

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AccountDto [id=" + id + ", type=" + type /*+ ", accountNumber=" + accountNumber */ + ", description="
				+ description + ", userId=" + userId + "]";
	}		
}
