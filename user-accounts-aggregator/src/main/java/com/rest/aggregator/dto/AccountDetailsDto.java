package com.rest.aggregator.dto;

import java.io.Serializable;

public class AccountDetailsDto implements Serializable {
    
	private static final long serialVersionUID = 1L;

    private Long accountId;

    private String type;
    
    private String accountNumber;

    private String description;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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

	@Override
	public String toString() {
		return "Account Details [accountId=" + accountId + ", type=" + type + ", accountNumber="
				+ accountNumber + ", description=" + description + "]";
	}	
}