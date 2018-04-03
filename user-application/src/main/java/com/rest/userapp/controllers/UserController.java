package com.rest.userapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.Account;
import com.rest.entity.User;
import com.rest.userapp.service.UserService;
import com.rest.utils.ApplicationConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
@Api(value = "user-application", description = "Operations of user in User Application")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	// -------------------Create a User-------------------------------------------
	@ApiOperation(value = "Add an user")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = ApplicationConstants.CREATED_201),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403)
    	}
    )
	@PostMapping
	public User save(@Valid @RequestBody User user) {				
		return userService.save(user);
	}

	// ------------------- Update a User-------------------------------------------
	@ApiOperation(value = "Update an user")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403)
    	}
    )
	@PutMapping(value = "/{id}")
	public User update(@PathVariable("id") long id, @RequestBody User user) {
		return userService.update(user);
	}

	// -------------------Retrieve Single User ------------------------------------------
	@ApiOperation(value = "Search an user with an ID", response = User.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )
	@GetMapping(value = "/{id}")
	public User getById(@PathVariable("id") long id) {		
		return userService.findById(id);
	}

	// -------------------Retrieve All User ------------------------------------------	
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
		return userService.findAll();
	}
	
	// -------------------Retrieve All Users and Accounts------------------------------------------
	@ApiOperation(value = "Retrieve All Users and Accounts",response = Iterable.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )
	@GetMapping(value = "/allUsersAndAccounts")
	public List<User> getAllUsersAndAccounts() {
		return userService.findAllUsersAndAccounts();
	}
	
	// -------------------Retrieve All Accounts for a user ------------------------------------------
	@ApiOperation(value = "Retrieve All Accounts for a user",response = Iterable.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )
	@GetMapping(value = "/allAccounts/{id}")
	public List<Account> getAllAccounts(@PathVariable("id") long id) {
		
		return userService.findAllAccounts(id);
	}
	
	// ------------------- Delete a User -----------------------------------------
	@ApiOperation(value = "Delete an user")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") long id) {
		userService.delete(id);
	}

	// ------------------- Delete All User-----------------------------
	@ApiOperation(value = "Delete all users")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )
	@DeleteMapping(value = "/")
	public void deleteAll() {
		userService.deleteAll();
	}
}