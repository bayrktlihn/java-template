package io.bayrktlihn.template.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Validator {


    public static boolean validateCardNumber(String cardNumber) {
        if (!StringUtil.allCharacterIsDigit(cardNumber)) {
            throw new RuntimeException();
        }

        return luhnAlgorithm(cardNumber);
    }

    private static boolean luhnAlgorithm(String cardNumber) {
        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();

        boolean evenNumber = false;
        for (int i = cardNumber.length() - 1; i > -1; i--) {
            String digit = StringUtil.stringAt(cardNumber, i);
            if (evenNumber) {
                evenNumbers.add(Integer.valueOf(digit));
            } else {
                oddNumbers.add(Integer.valueOf(digit));
            }
            evenNumber = !evenNumber;
        }

        int resultOfOddNumbers = oddNumbers.stream().mapToInt(item -> item).sum();
        int resultOfEvenNumbers = evenNumbers.stream().map(item -> item * 2).flatMap(item -> {
            if (item > 9) {
                return Stream.of(item % 10, item / 10);
            }
            return Stream.of(item);
        }).mapToInt(item -> item).sum();

        int totalResult = resultOfEvenNumbers + resultOfOddNumbers;

        return totalResult % 10 == 0;


    }

    public static boolean validateVakifBankCardNumber(String cardNumber) {
        Pattern compile = Pattern.compile("^((418342|418343|418344|418345|438331|450803|454318|454358|454359|454360|473998|474301|474340|474508|479610|510152|525382|540667|540668|543771|548237|552096|553058|559289|650173|650987|650990|979204)\\d{2}|47430810|47430811)\\d{8}$");
        Matcher matcher = compile.matcher(cardNumber);
        return matcher.matches();
    }

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

        if (!StringUtil.allCharacterIsDigit(turkishIdentifierNumber)) {
            return false;
        }

        int indexOfEleventhDigit = turkishIdentifierNumber.length() - 1;
        int indexOfTenthDigit = indexOfEleventhDigit - 1;
        int indexOfNinthDigit = indexOfTenthDigit - 1;
        int indexOfFirstDigit = 0;
        int indexOfSecondDigit = indexOfFirstDigit + 1;
        int tenthDigit = Integer.parseInt(StringUtil.stringAt(turkishIdentifierNumber, indexOfTenthDigit));
        int eleventhDigit = Integer.parseInt(StringUtil.stringAt(turkishIdentifierNumber, indexOfEleventhDigit));
        int firstDigit = Integer.parseInt(StringUtil.stringAt(turkishIdentifierNumber, indexOfFirstDigit));

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


}
