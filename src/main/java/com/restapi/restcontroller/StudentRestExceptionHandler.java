package com.restapi.restcontroller;

import com.restapi.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    //Add exception handling code here
    //Add exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        //create a studentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMesssage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //Return Response Entity
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    //Add another exception handler .. to catch any type of exception(catch all)
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        //create a studentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMesssage(exc.getMessage());
        error.setMesssage("Invalid input");
        error.setTimeStamp(System.currentTimeMillis());

        //Return Response Entity
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }


}
