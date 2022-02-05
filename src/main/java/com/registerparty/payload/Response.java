package com.registerparty.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private String rc;
    private String status;
    private T data;
    private String message;

    public Response(T object)
    {
        this.rc = "00";
        this.status = SUCCESS;
        this.data = object;
    }


    public void setError(String message) {
        this.rc = "99";
        this.status = ERROR;
        this.message = message;
    }

    public void setError(String rc, String message) {
        this.rc = rc;
        this.status = ERROR;
        this.message = message;
    }

    public void setSuccess(T object){
        this.rc = "00";
        this.status = SUCCESS;
        this.data = object;
    }

    public void setSuccess(String message, T object){
        this.rc = "00";
        this.status = SUCCESS;
        this.message = message;
        this.data = object;
    }
}
