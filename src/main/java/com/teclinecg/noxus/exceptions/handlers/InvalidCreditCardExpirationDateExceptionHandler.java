package com.teclinecg.noxus.exceptions.handlers;

import com.teclinecg.noxus.dtos.ExceptionHandlerDto;
import com.teclinecg.noxus.exceptions.InvalidCellphoneNumberException;
import com.teclinecg.noxus.exceptions.InvalidCreditCardExpirationDateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class InvalidCreditCardExpirationDateExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(InvalidCreditCardExpirationDateException.class)
    public ResponseEntity<ExceptionHandlerDto<String>> handle(InvalidCreditCardExpirationDateException ex) {
        ExceptionHandlerDto<String> handlerDto = new ExceptionHandlerDto<>();

        Map<String, String> errors = new HashMap<>();
        errors.put("status", ex.getMessage());

        handlerDto.setErrors(errors);
        handlerDto.setHttpStatus(HttpStatus.BAD_REQUEST);
        handlerDto.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Z")));

        handlerDto.setValidExamples(List.of("12/31"));

        return ResponseEntity.badRequest().body(handlerDto);
    }
}
