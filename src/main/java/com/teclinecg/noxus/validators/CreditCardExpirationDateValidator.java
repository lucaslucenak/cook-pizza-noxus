package com.teclinecg.noxus.validators;

import com.teclinecg.noxus.interfaces.ValidatorInterface;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardExpirationDateValidator implements ValidatorInterface {

    private static final String CREDIT_CARD_EXPIRATION_DATE_REGEX = "(0[1-9]|1[0-2])/\\\\d{2}";

    @Override
    public boolean isValid(String creditCardExpirationDate) {
        Pattern pattern = Pattern.compile(CREDIT_CARD_EXPIRATION_DATE_REGEX);
        Matcher matcher = pattern.matcher(creditCardExpirationDate);

        // If the expiration date doesn't match the pattern MM/yy
        if (!matcher.matches()) {
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        YearMonth yearMonth = YearMonth.parse(creditCardExpirationDate, formatter);
        YearMonth now = YearMonth.now();
        return yearMonth.isAfter(now);
    }
}
