package com.rest.useraggregate.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.User;
import com.rest.useraggregate.service.UserAggregateService;
import com.rest.utils.ApplicationConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/useraggregate")
@Api(value = "user-aggregate-application", description = "Operations of user and account in User Aggregate Application")
public class UserAggregateController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Autowired
	private UserAggregateService userAggregateService;
		
	// -------------------Retrieve All Users ------------------------------------------
	@ApiOperation(value = "View a list of available users",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )
	@GetMapping(value = "/")
	public List<User> getAll() {		
		return userAggregateService.findAll();
	}	
}