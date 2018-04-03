package com.rest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Account implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
    @ApiModelProperty(notes = "Database generated Account ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(notes = "Account type e.g. Savings, Current, Loan")
    @NotNull
    @Size(max = 15)
    private String type;
    
    @ApiModelProperty(notes = "Account number")
    @NotNull
    @Size(max = 15)
    private String accountNumber;

    @ApiModelProperty(notes = "Account Description")
    @NotNull
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    public Account() {
    	
    }
    
	public Account(String type, String accountNumber, String description, User user) {
		super();
		this.type = type;
		this.accountNumber = accountNumber;
		this.description = description;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", type=" + type + ", accountNumber=" + accountNumber + ", description="
				+ description + ", user=" + user + "]";
	}	
}
