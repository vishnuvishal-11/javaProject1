package com.example.dbproject.globalexceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityException() {
        return new ResponseEntity<String>("id not found",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ParseException.class)
    public ResponseEntity<String> handleParseException() {
        return new ResponseEntity<String>("a parsing error occurred , please try later",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLException() {
        return new ResponseEntity<String>("An error occurred reading file, change input values",
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = PropertyReferenceException.class)
    public ResponseEntity<String> handlePropertyException() {
        return new ResponseEntity<String>("No such property exists..", HttpStatus.BAD_REQUEST);
    }
    @Override
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldError = result.getFieldErrors();
        List<String> errors = new ArrayList<>();
        for(FieldError error : fieldError) {
            errors.add(error.getDefaultMessage());}
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),"Bad request", errors);
        return this.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }
}
