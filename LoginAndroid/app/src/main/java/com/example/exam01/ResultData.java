package com.example.exam01;

/**
 * Created by 최예찬 on 2016-08-05.
 */
public class ResultData {
    private boolean success;
    private String result;
    private String message;

    public ResultData(boolean success, String result, String message) {
        this.success = success;
        this.result = result;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
