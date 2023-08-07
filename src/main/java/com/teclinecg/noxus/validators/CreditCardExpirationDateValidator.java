package com.teclinecg.noxus.validators;

import com.teclinecg.noxus.exceptions.InvalidCreditCardExpirationDateException;
import com.teclinecg.noxus.interfaces.ValidatorInterface;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardExpirationDateValidator implements ConstraintValidator<CreditCardExpirationDate, String> {

    private static final String CREDIT_CARD_EXPIRATION_DATE_REGEX = "^(0[1-9]|1[0-2])/\\\\d{2}$";

    @Override
    public void initialize(CreditCardExpirationDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String creditCardExpirationDate, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile(CREDIT_CARD_EXPIRATION_DATE_REGEX);
        Matcher matcher = pattern.matcher(creditCardExpirationDate);

        // If the expiration date doesn't match the pattern MM/yy
        if (matcher.matches() || creditCardExpirationDate.length() != 5) {
            throw new InvalidCreditCardExpirationDateException("Field Expiration Date needs to have the following pattern: MM/yy");
        }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth yearMonth = YearMonth.parse(creditCardExpirationDate, formatter);

            if (!yearMonth.isAfter(YearMonth.now())) {
                throw new InvalidCreditCardExpirationDateException("Field Expiration Date isn't dated after from now.");
            }
        // Matches with MM/yyyy pattern
//        else if (creditCardExpirationDate.matches("^(0[1-9]|1[0-2])/\\d{4}$")) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
//            YearMonth yearMonth = YearMonth.parse(creditCardExpirationDate, formatter);
//
//            if (yearMonth.getMonthValue() > 12) {
//                throw new InvalidCreditCardExpirationDateException("Month is greater than 12");
//            }
//            if (yearMonth.getMonthValue() < 1) {
//                throw new InvalidCreditCardExpirationDateException("Month is lower than 1");
//            }
//            if (!yearMonth.isAfter(YearMonth.now())) {
//                throw new InvalidCreditCardExpirationDateException("Field Expiration Date isn't dated after from now.");
//            }
//        }

        return true;
    }
}
