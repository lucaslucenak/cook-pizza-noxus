package com.teclinecg.noxus.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CreditCardNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CreditCardNumber {
    String message() default "Invalid Credit Card Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
