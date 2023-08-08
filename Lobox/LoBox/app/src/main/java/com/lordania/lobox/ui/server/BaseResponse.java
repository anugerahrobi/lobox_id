package com.lordania.lobox.ui.server;

import com.google.gson.annotations.SerializedName;

public class BaseResponse <T> {
    @SerializedName("error")
    private boolean error;
    @SerializedName("success")
    private int success;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private T data;
    public boolean isError() {
        return error;
    }
    public void setError(boolean error) {
        this.error = error;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public int isSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
