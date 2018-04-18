package com.rest.aggregator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.aggregator.dto.UserAccountsAggregatorDto;
import com.rest.aggregator.enums.UserAccountsAggregatorResponseStatus;
import com.rest.aggregator.service.UserAccountsAggregatorService;
import com.rest.aggregator.wrapper.UserAccountsAggregatorResponseWrapper;


@RestController
@RequestMapping("/useraccounts")
//@Api(value = "user-accounts-aggregator", description = "Operations of user and account in User Aggregate Application")
public class UserAccountsAggregatorController {		
	@Autowired
	private UserAccountsAggregatorService aggregatorService;
		
	// -------------------Retrieve All Users ------------------------------------------
	/*@ApiOperation(value = "View a list of available users along their account details",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )*/
	@GetMapping(value = "/")
	public ResponseEntity<UserAccountsAggregatorResponseWrapper<List<UserAccountsAggregatorDto>>> getAll() {		
		return new ResponseEntity<UserAccountsAggregatorResponseWrapper<List<UserAccountsAggregatorDto>>>(
			new UserAccountsAggregatorResponseWrapper<List<UserAccountsAggregatorDto>>(
				UserAccountsAggregatorResponseStatus.SUCCESS,aggregatorService.findAllUserAndAccounts()),HttpStatus.OK);
	}	
}