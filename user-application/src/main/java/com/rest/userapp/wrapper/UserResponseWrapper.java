package com.rest.userapp.wrapper;

import java.util.List;

import com.rest.userapp.enums.UserResponseStatus;

public class UserResponseWrapper<T> {

    private UserResponseStatus status;
    private T data;
    private List<String> errors;

    public UserResponseWrapper() {
        // no-arg constructor
    }

    public UserResponseWrapper(UserResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public UserResponseWrapper(UserResponseStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public UserResponseStatus getStatus() {
        return status;
    }

    public void setStatus(UserResponseStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "UserResponseWrapper [status=" + status + ", data=" + data + ", errors=" + errors + "]";
	}	
}
