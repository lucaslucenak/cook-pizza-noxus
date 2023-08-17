package com.teclinecg.noxus.exceptions.handlers;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.teclinecg.noxus.dtos.ExceptionHandlerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice
public class UnrecognizedPropertyExceptionHandler {

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    @ExceptionHandler(UnrecognizedPropertyException.class)
//    public ResponseEntity<ExceptionHandlerDto<?>> handle(UnrecognizedPropertyException ex) {
//        ExceptionHandlerDto<?> handlerDto = new ExceptionHandlerDto<>();
//
//        Map<String, String> errors = new HashMap<>();
//        errors.put("status", ex.getMessage());
//
//        handlerDto.setErrors(errors);
//        handlerDto.setHttpStatus(HttpStatus.BAD_REQUEST);
//        handlerDto.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Z")));
//
//        return ResponseEntity.badRequest().body(handlerDto);
//    }
}
