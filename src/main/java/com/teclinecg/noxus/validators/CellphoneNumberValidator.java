package com.teclinecg.noxus.validators;

import com.teclinecg.noxus.exceptions.InvalidCellphoneNumberException;
import com.teclinecg.noxus.interfaces.ValidatorInterface;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CellphoneNumberValidator implements ConstraintValidator<CellphoneNumber, String> {

    private static final String CELLPHONE_NUMBER_REGEX = "^\\(?(?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}$";

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
