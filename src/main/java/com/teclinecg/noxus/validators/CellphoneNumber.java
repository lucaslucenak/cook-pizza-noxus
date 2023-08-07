package com.teclinecg.noxus.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CellphoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CellphoneNumber {
    String message() default "Invalid Cellphone Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
