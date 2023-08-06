package com.teclinecg.noxus.exceptions.handlers;

import com.teclinecg.noxus.dtos.ExceptionHandlerDto;
import com.teclinecg.noxus.exceptions.InvalidCellphoneNumberException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@ControllerAdvice
public class InvalidCellphoneNumberExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(InvalidCellphoneNumberException.class)
    public ResponseEntity<ExceptionHandlerDto<String>> handle(InvalidCellphoneNumberException ex) {
        ExceptionHandlerDto<String> handlerDto = new ExceptionHandlerDto<>();

        Map<String, String> errors = new HashMap<>();
        errors.put("status", ex.getMessage());

        handlerDto.setErrors(errors);
        handlerDto.setHttpStatus(HttpStatus.BAD_REQUEST);
        handlerDto.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Z")));

        handlerDto.setValidExamples(List.of("83123456789"));

        return ResponseEntity.badRequest().body(handlerDto);
    }
}
