package com.teclinecg.noxus.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ExceptionHandlerDto {

    private Map<String, String> errors;
    private HttpStatus httpStatus;
    private ZonedDateTime zonedDateTime;


    public ExceptionHandlerDto() {
    }

    public ExceptionHandlerDto(Map<String, String> errors, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
        this.errors = errors;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }
}
