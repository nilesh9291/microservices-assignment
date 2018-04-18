package com.rest.accountapp.wrapper;

import java.util.List;

import com.rest.accountapp.enums.AccountResponseStatus;

public class AccountResponseWrapper<T> {

    private AccountResponseStatus status;
    private T data;
    private List<String> errors;

    public AccountResponseWrapper() {
        // no-arg constructor
    }

    public AccountResponseWrapper(AccountResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public AccountResponseWrapper(AccountResponseStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public AccountResponseStatus getStatus() {
        return status;
    }

    public void setStatus(AccountResponseStatus status) {
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
		return "AccountResponseWrapper [status=" + status + ", data=" + data + ", errors=" + errors + "]";
	}	
}
