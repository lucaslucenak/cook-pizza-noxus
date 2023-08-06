package com.teclinecg.noxus.validators;

import com.teclinecg.noxus.exceptions.InvalidCellphoneNumberException;
import com.teclinecg.noxus.interfaces.ValidatorInterface;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CellphoneNumberValidator implements ConstraintValidator<CellphoneNumber, String> {

    private static final String CELLPHONE_NUMBER_REGEX = "^(\\(\\d{2}\\)|\\d{2})\\s9\\d{4}-\\d{4}$";

    @Override
    public void initialize(CellphoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cellphoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile(CELLPHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(cellphoneNumber);

        if (!matcher.matches()) {
            throw new InvalidCellphoneNumberException("Invalid cellphone number.");
        }
        return true;
    }
}
