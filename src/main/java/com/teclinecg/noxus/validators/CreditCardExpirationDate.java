package com.teclinecg.noxus.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CreditCardExpirationDateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CreditCardExpirationDate {
    String message() default "Invalid Credit Card Expiration Date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
