package com.rest.aggregator.wrapper;

import java.util.List;

import com.rest.aggregator.enums.UserAccountsAggregatorResponseStatus;
import com.rest.aggregator.exceptions.ErrorDetails;

public class UserAccountsAggregatorResponseWrapper<T> {

    private UserAccountsAggregatorResponseStatus status;
    private T data;
    private List<ErrorDetails> errorDetails;

    public UserAccountsAggregatorResponseWrapper() {
        // no-arg constructor
    }

    public UserAccountsAggregatorResponseWrapper(UserAccountsAggregatorResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public UserAccountsAggregatorResponseWrapper(UserAccountsAggregatorResponseStatus status, T data, List<ErrorDetails> errorDetails) {
        this.status = status;
        this.data = data;
        this.errorDetails = errorDetails;
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

    public List<ErrorDetails> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(List<ErrorDetails> errorDetails) {
        this.errorDetails = errorDetails;
    }

    @Override
    public String toString() {
        return "UserAccountsAggregatorResponseWrapper [status=" + status + ", data=" + data + ", errorDetails=" + errorDetails + "]";
    }
}
