package com.rest.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class User implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated user ID")
    private Long id;

    @ApiModelProperty(notes = "The unique username of the user.")
    @NotNull
    @Size(max = 20)
    private String userName;

    @ApiModelProperty(notes = "User's first name")
    @NotNull(message = "first name can not be null.")
    @Size(max = 20)
    private String firstName;

    @ApiModelProperty(notes = "User's last name")
    @NotNull(message = "last name can not be null.")
    @Size(max = 20)
    private String lastName;
    
    @ApiModelProperty(notes = "User's age")
    @NotNull
    private int age;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    @JsonIgnore
    private Set<Account> accountSet = new HashSet<Account>();

    public User() {
    	
    }
    
	public User(String userName, String firstName, String lastName, int age, Set<Account> accounts) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.accountSet = accounts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Account> getAccounts() {
		return accountSet;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accountSet = accounts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", age=" + age + ", accountSet=" + accountSet + "]";
	}
		
}
