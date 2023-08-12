package io.bayrktlihn.template.util;

import java.math.BigDecimal;

public class StringUtil {


    public static final String DEFAULT_MASK_CHARACTER = "*";


    public static int compareToByNumber(String num1, String num2) {
        if (allCharacterIsDigit(num1) && allCharacterIsDigit(num2)) {
            return new BigDecimal(num1).compareTo(new BigDecimal(num2));
        }
        throw new IllegalArgumentException();
    }

    public static String stringAt(String str, int index) {
        return str.substring(index, index + 1);
    }

    public static String removeRepeatSpace(String str) {
        return str.replaceAll(" {2,}", " ");
    }

    public static String mask(String str, int showFirstCharacterLength, int showLastCharacterLength, String maskCharacter) {
        if (str == null || maskCharacter == null) {
            throw new IllegalArgumentException();
        }

        if (showFirstCharacterLength < 0 || showLastCharacterLength < 0) {
            throw new IllegalArgumentException();
        }

        int showedCharactersLength = showFirstCharacterLength + showLastCharacterLength;

        if (showedCharactersLength > str.length()) {
            throw new IllegalArgumentException();
        }

        String firstCharacters = str.substring(0, showFirstCharacterLength);
        String lastCharacters = str.substring(str.length() - showLastCharacterLength);
        int maskCharacterLength = str.length() - showedCharactersLength;
        return firstCharacters + times(maskCharacter, maskCharacterLength) + lastCharacters;
    }

    public static String mask(String str, int showFirstCharacterLength, int showLastCharacterLength) {
        return mask(str, showFirstCharacterLength, showLastCharacterLength, DEFAULT_MASK_CHARACTER);
    }

    public static String mask(String str, int showFirstCharacterLength, String maskCharacter) {
        return mask(str, showFirstCharacterLength, 0, maskCharacter);
    }

    public static String times(String str, int times) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (times < 1) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }


    public static boolean allCharacterIsDigit(String str) {
        if (str == null) {
            return false;
        }

        for (int i = 0; i < str.toCharArray().length; i++) {
            char c = str.charAt(i);

            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;

    }

}
