package com.teclinecg.noxus.validators;

import com.teclinecg.noxus.interfaces.ValidatorInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ValidatorInterface {

    // Credits:
    // https://www.baeldung.com/java-email-validation-regex

    // BASED ON RFC 5322
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Override
    public boolean isValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
