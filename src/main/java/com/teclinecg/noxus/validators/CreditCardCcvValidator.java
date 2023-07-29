package com.teclinecg.noxus.validators;

import com.teclinecg.noxus.interfaces.ValidatorInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardCcvValidator implements ValidatorInterface {

    private static final String CREDIT_CARD_CCV_REGEX = "\\d(3,4)";

    @Override
    public boolean isValid(String creditCardCCV) {
        Pattern pattern = Pattern.compile(CREDIT_CARD_CCV_REGEX);
        Matcher matcher = pattern.matcher(creditCardCCV);
        return matcher.matches();
    }
}
