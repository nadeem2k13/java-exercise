package com.sss.java_exerise.dto;

import lombok.Data;

@Data
public class ApiResponse {
    private String msg;
    private int code;   // code 0 for failed - code 1 for success
    private Object data;

    public ApiResponse() {
    }

    public ApiResponse(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
