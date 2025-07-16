package com.capstone.capstoneproject_ecomapp.exceptions;

import com.capstone.capstoneproject_ecomapp.dtos.ErrorDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ErrorDto handleNullPointerException() {
        ErrorDto error = new ErrorDto();
        error.setStatus("Failure");
        error.setMessage("NullPointer Exception occurred");
        return error;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDto handleProductNotFoundException(ProductNotFoundException e) {
        ErrorDto error = new ErrorDto();
        error.setStatus("Failure");
        error.setMessage(e.getMessage());
        return error;
    }
}
