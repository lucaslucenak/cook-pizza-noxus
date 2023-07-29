package com.teclinecg.noxus.validators;

import com.teclinecg.noxus.interfaces.ValidatorInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CellphoneNumberValidator implements ValidatorInterface {

    private static final String CELLPHONE_NUMBER_REGEX = "^(\\(\\d{2}\\)|\\d{2})\\s9\\d{4}-\\d{4}$";

    @Override
    public boolean isValid(String cellphoneNumber) {
        Pattern pattern = Pattern.compile(CELLPHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(cellphoneNumber);
        return matcher.matches();
    }
}
