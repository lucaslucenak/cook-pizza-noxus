package com.teclinecg.noxus.validators;

import com.teclinecg.noxus.interfaces.ValidatorInterface;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CreditCardNumberValidator implements ConstraintValidator<CreditCardNumber, String> {

    @Override
    public void initialize(CreditCardNumber constraintAnnotation) {
    }

    @Override
    // Credits:
    // https://www.geeksforgeeks.org/program-credit-card-number-validation/
    public boolean isValid(String creditCardNumber, ConstraintValidatorContext constraintValidatorContext) {
        long parsedCreditCardNumber = Long.parseLong(creditCardNumber);

        return (getSize(parsedCreditCardNumber) >= 13 &&
                getSize(parsedCreditCardNumber) <= 16) &&
                (prefixMatched(parsedCreditCardNumber, 4) ||
                        prefixMatched(parsedCreditCardNumber, 5) ||
                        prefixMatched(parsedCreditCardNumber, 37) ||
                        prefixMatched(parsedCreditCardNumber, 6)) &&
                ((sumOfDoubleEvenPlace(parsedCreditCardNumber) +
                        sumOfOddPlace(parsedCreditCardNumber)) % 10 == 0);
    }

    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

        return sum;
    }

    // Return this number if it is a single digit, otherwise,
    // return the sum of the two digits
    public static int getDigit(int number) {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }

    // Return sum of odd-place digits in number
    public static int sumOfOddPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }

    // Return true if the digit d is a prefix for number
    public static boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize(d)) == d;
    }

    // Return the number of digits in d
    public static int getSize(long d) {
        String num = d + "";
        return num.length();
    }

    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    public static long getPrefix(long number, int k) {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }
}
