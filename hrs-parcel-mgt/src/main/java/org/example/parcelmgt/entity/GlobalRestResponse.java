package org.example.parcelmgt.entity;

import lombok.Data;

/**
 * Global Rest API return payload, with custom response message and payload
 * @param <T> the payload to be returned
 */
@Data
public class GlobalRestResponse<T> {
    private String message;
    private T data;

    public static GlobalRestResponse<Object> ok() {
        GlobalRestResponse<Object> response = new GlobalRestResponse<>();
        response.setMessage("OK");
        return response;
    }

    public static <T> GlobalRestResponse<T> ok(T data) {
        GlobalRestResponse<T> response = new GlobalRestResponse<>();
        response.setMessage("OK");
        response.setData(data);
        return response;
    }
}
