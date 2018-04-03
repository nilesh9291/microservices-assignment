package com.rest.dto;

import javax.validation.constraints.NotNull;

//@Data
public class AccountDto {   
   	private Long id;

    @NotNull(message = "Type can not be null.")
    private String type;
    
    @NotNull(message = "Account Number can not be null.")
    private String accountNumber;

    @NotNull(message = "Description can not be null.")
    private String description;

    private long userId;
    
    public AccountDto() {
    	
    }

	public AccountDto(String type, String accountNumber, String description, long userId) {
		super();
		this.type = type;
		this.accountNumber = accountNumber;
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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

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
		return "AccountDto [id=" + id + ", type=" + type + ", accountNumber=" + accountNumber + ", description="
				+ description + ", userId=" + userId + "]";
	}		
}
