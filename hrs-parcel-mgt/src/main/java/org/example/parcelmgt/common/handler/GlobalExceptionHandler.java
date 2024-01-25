package org.example.parcelmgt.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.parcelmgt.common.exception.GuestNotFoundException;
import org.example.parcelmgt.entity.GlobalRestResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GuestNotFoundException.class)
    public GlobalRestResponse<Object> handleServiceException(GuestNotFoundException e) {
        return GlobalRestResponse.error(e.getMessage(), null);
    }
}
