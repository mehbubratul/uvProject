package org.ulventech;

import java.util.Arrays;

public class RandomStringGenerationService {

    //region private fields
    private static final int STRING_LENGTH_FOR_EACH_LINE = 100;
    private static final int MIN_INT_ACCEPTED_VALUE = 1;
    private static final int MAX_INT_ACCEPTED_VALUE = (int) (Math.pow(2, 30) - 1);
    //endregion

    //region public methods
    public static boolean isInputValueValid(String input) {

        if (new StringUtil().isNullOrEmptyOrBlank(input)) {
            return false;
        }

        int validIntValue;

        try {
            validIntValue = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.printf("%s is not an integer type%n", input);
            // System.exit(0);
            return false;
        }

        System.out.printf("%s is integer type%n", input);

        if (validIntValue < MIN_INT_ACCEPTED_VALUE) {
            System.out.println("Number value must be between : " + MIN_INT_ACCEPTED_VALUE + " & " + MAX_INT_ACCEPTED_VALUE);
            return false;
        } else if (validIntValue > MAX_INT_ACCEPTED_VALUE) {
            System.out.println("Number value must be between : " + MIN_INT_ACCEPTED_VALUE + " & " + MAX_INT_ACCEPTED_VALUE);
            return false;
        }

        return true;
    }

    public static byte[] getInitialBytes() {
        byte[] bytes = new byte[STRING_LENGTH_FOR_EACH_LINE];
        Arrays.fill(bytes, (byte) 'a');
        return bytes;
    }

    public static byte[] getConsecutiveBytes(byte[] input) {
        if(input.length == 0 ) return input;
        if(input.length != 100 ) return input;

        for (int j = 0; j < STRING_LENGTH_FOR_EACH_LINE; j++) {
            input[j]++;
            if (input[j] <= 'z') {
                break;
            } else {
                input[j] = 'a';
            }
        }
        return input;
    }
    //endregion

}
