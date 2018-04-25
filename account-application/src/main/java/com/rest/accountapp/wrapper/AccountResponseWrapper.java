package com.rest.accountapp.wrapper;

import java.util.List;

import com.rest.accountapp.enums.AccountResponseStatus;
import com.rest.accountapp.exceptions.ErrorDetails;

public class AccountResponseWrapper<T> {

    private AccountResponseStatus status;
    private T data;
    private List<ErrorDetails> errorDetails;

    public AccountResponseWrapper() {
        // no-arg constructor
    }

    public AccountResponseWrapper(AccountResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public AccountResponseWrapper(AccountResponseStatus status, T data, List<ErrorDetails> errorDetails) {
        this.status = status;
        this.data = data;
        this.errorDetails = errorDetails;
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

    public List<ErrorDetails> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(List<ErrorDetails> errorDetails) {
        this.errorDetails = errorDetails;
    }

    @Override
    public String toString() {
        return "AccountResponseWrapper [status=" + status + ", data=" + data + ", errorDetails=" + errorDetails + "]";
    }
}
