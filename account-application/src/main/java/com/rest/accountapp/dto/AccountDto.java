package com.rest.accountapp.dto;

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

public class AccountDto implements Serializable {
    
	private static final long serialVersionUID = 1L;

    /**
     * Database generated Account ID
     */
    @ApiModelProperty(notes = "Database generated Account ID")
    private Long accountId;

    /**
     * Account type e.g. Savings, Current, Loan
     */
    @ApiModelProperty(notes = "Account type e.g. Savings, Current, Loan")
    @NotEmpty(message = "Account type can not be empty.")
	@Size(min=2, max=20, message = "Account type must be between 2 to 15 characters.")
    private String type;
    
    /**
     * Account number
     */
    @ApiModelProperty(notes = "Account number")  
    private String accountNumber;

    /**
     * Account Description
     */
    @ApiModelProperty(notes = "Account Description")
	@NotEmpty(message = "Account Description can not be empty.")
	@Size(min=2, max=50, message = "Account Description must be between 2 to 50 characters.")
    private String description;

    /**
     * User Id. There must be a user associated with the account
     */
    @Min(value = 1, message="User Id must be above 0")
	private Long userId;
    
    public AccountDto() {
    	
    }

	public AccountDto(Long accountId, String type, String accountNumber, String description, Long userId) {
		this.accountId = accountId;
		this.type = type;
		this.accountNumber = accountNumber;
		this.description = description;
		this.userId = userId;
	}

	public AccountDto(String type, String accountNumber, String description, Long userId) {
		super();
		this.type = type;
		this.accountNumber = accountNumber;
		this.description = description;
		this.userId = userId;
	}


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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AccountDto [accountId=" + accountId + ", type=" + type + ", accountNumber=" + accountNumber + ", description="
				+ description + ", userId=" + userId + "]";
	}		
}
