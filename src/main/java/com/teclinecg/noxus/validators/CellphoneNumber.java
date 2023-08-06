package com.teclinecg.noxus.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CellphoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface CellphoneNumber {
    String message() default "Invalid Cellphone Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
