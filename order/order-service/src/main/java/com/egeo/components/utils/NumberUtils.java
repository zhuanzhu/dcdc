package com.egeo.components.utils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class NumberUtils {
    private static final Pattern DECIMAL_PATTERN = Pattern.compile("\\d+(\\.\\d+)?");

    public static String retainDecimalPlaces(String input, int minDecimalPlaces) {
        if (!DECIMAL_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("Input is not a valid decimal number.");
        }

        BigDecimal bd = new BigDecimal(input);
        bd = bd.setScale(minDecimalPlaces, BigDecimal.ROUND_HALF_UP);
        return bd.stripTrailingZeros().toPlainString(); // Remove trailing zeros and convert to string
    }

    public static String retainDecimalPlaces(String input) {
        if (!DECIMAL_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("Input is not a valid decimal number.");
        }

        BigDecimal bd = new BigDecimal(input);
        return bd.stripTrailingZeros().toPlainString(); // Remove trailing zeros and convert to string
    }

    public static void main(String[] args) {
        String input = "1230.00";
        String output = retainDecimalPlaces(input); // Will output "123.5"
        System.out.println(output);
    }
}
