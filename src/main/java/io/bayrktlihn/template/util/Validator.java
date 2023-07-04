package io.bayrktlihn.template.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean validateTurkishVehiclePlate(String turkishPlate) {
        if (turkishPlate == null) {
            return false;
        }

        String regex = "^((0[1-9])|([1-7][0-9])|(8[01])) (([A-Z] [0-9]{4,5})|([A-Z]{2} [0-9]{3,4})|([A-Z]{3} [0-9]{2}))$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(turkishPlate);
        return matcher.matches();
    }

    public static boolean validateTurkishIdentifierNumber(String turkishIdentifierNumber) {

        if (turkishIdentifierNumber == null) {
            return false;
        }

        if (turkishIdentifierNumber.length() != 11) {
            return false;
        }

        if (!allCharacterIsDigit(turkishIdentifierNumber)) {
            return false;
        }

        int indexOfEleventhDigit = turkishIdentifierNumber.length() - 1;
        int indexOfTenthDigit = indexOfEleventhDigit - 1;
        int indexOfNinthDigit = indexOfTenthDigit - 1;
        int indexOfFirstDigit = 0;
        int indexOfSecondDigit = indexOfFirstDigit + 1;
        int tenthDigit = Integer.parseInt(String.valueOf(turkishIdentifierNumber.charAt(indexOfTenthDigit)));
        int eleventhDigit = Integer.parseInt(String.valueOf(turkishIdentifierNumber.charAt(indexOfEleventhDigit)));
        int firstDigit = Integer.parseInt(String.valueOf(turkishIdentifierNumber.charAt(indexOfFirstDigit)));

        if (firstDigit == 0) {
            return false;
        }

        int totalOddDigitNumbers = 0;
        for (int i = indexOfFirstDigit; i < indexOfTenthDigit; i += 2) {
            totalOddDigitNumbers += Integer.parseInt(String.valueOf(turkishIdentifierNumber.charAt(i)));
        }

        int totalEvenDigitNumbers = 0;
        for (int i = indexOfSecondDigit; i < indexOfNinthDigit; i += 2) {
            totalEvenDigitNumbers += Integer.parseInt(String.valueOf(turkishIdentifierNumber.charAt(i)));
        }

        int differenceTotalDigits = totalOddDigitNumbers * 7 - totalEvenDigitNumbers;
        if (differenceTotalDigits % 10 != tenthDigit) {
            return false;
        }

        int totalOfFirstToTenth = 0;
        for (int i = 0; i < indexOfEleventhDigit; i++) {
            totalOfFirstToTenth += Integer.parseInt(String.valueOf(turkishIdentifierNumber.charAt(i)));
        }

        if (totalOfFirstToTenth % 10 != eleventhDigit) {
            return false;
        }

        return true;

    }

    private static boolean allCharacterIsDigit(String text) {
        if (text == null) {
            return false;
        }

        for (int i = 0; i < text.toCharArray().length; i++) {
            char c = text.charAt(i);

            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;

    }


}
