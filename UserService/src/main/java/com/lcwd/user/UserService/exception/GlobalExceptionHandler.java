package com.lcwd.user.UserService.exception;

import com.lcwd.user.UserService.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class can be used to handle in entire project
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFound ex){
        String message = ex.getMessage();
         ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
         return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
