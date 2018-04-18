package com.rest.aggregator.dto;

import java.io.Serializable;

public class UserAccountsAggregatorDto implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private UserDetailsDto userDetailsDto;
		
	public UserAccountsAggregatorDto(UserDetailsDto userDetailsDto) {
		super();
		this.userDetailsDto = userDetailsDto;
	}

	public UserDetailsDto getUserDetailsDto() {
		return userDetailsDto;
	}

	public void setUserDetailsDto(UserDetailsDto userDetailsDto) {
		this.userDetailsDto = userDetailsDto;
	}

	@Override
	public String toString() {
		return "UserAccountsAggregatorDto [User Details=" + userDetailsDto + "]";
	}	
}



