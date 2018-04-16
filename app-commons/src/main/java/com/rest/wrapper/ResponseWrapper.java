package com.rest.wrapper;

import java.util.List;

import com.rest.enums.ResponseStatus;

public class ResponseWrapper<T> {

    private ResponseStatus status;
    private T data;
    private List<String> errors;

    public ResponseWrapper() {
        // no-arg constructor
    }

    public ResponseWrapper(ResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseWrapper(ResponseStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
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
		return "ResponseWrapper [status=" + status + ", data=" + data + ", errors=" + errors + "]";
	}	
}
