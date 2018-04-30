package com.rest.accountapp.wrapper;

import com.rest.accountapp.enums.AccountResponseStatus;
import com.rest.accountapp.exceptions.ErrorDetails;

import java.util.List;

public class AccountResponseWrapper<T> {

    private AccountResponseStatus status;
    private T data;
    private List<ErrorDetails> errorDetails;

    public AccountResponseWrapper() {
    }

    public AccountResponseWrapper(AccountResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public AccountResponseWrapper(AccountResponseStatus status, List<ErrorDetails> errorDetails) {
        this.status = status;
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
