package com.rest.aggregator.wrapper;

import java.util.List;

import com.rest.aggregator.enums.UserAccountsAggregatorResponseStatus;
import com.rest.aggregator.wrapper.UserAccountsAggregatorResponseWrapper;

public class UserAccountsAggregatorResponseWrapper<T> {

    private UserAccountsAggregatorResponseStatus status;
    private T data;
    private List<String> errors;

    public UserAccountsAggregatorResponseWrapper() {
        // no-arg constructor
    }

    public UserAccountsAggregatorResponseWrapper(UserAccountsAggregatorResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public UserAccountsAggregatorResponseWrapper(UserAccountsAggregatorResponseStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public UserAccountsAggregatorResponseStatus getStatus() {
        return status;
    }

    public void setStatus(UserAccountsAggregatorResponseStatus status) {
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
		return "UserAccountsAggregatorResponseWrapper [status=" + status + ", data=" + data + ", errors=" + errors + "]";
	}	
}
