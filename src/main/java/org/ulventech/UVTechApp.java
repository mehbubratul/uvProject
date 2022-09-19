package org.ulventech;

import java.util.Random;

public class UVTechApp {

    public static void main(String[] args) {
        performProcess(); //1073741823; 1,07,37,41,823
    }

    private static void performProcess() {

        String inputValue = ScanningUtil.doScanningAndGetIntValue();

        if (!RandomStringGenerationUtil.isInputValueValid(inputValue)) {
            return;
        }

        System.out.println("Please wait while the program is running... ");

        String filePath = FileUtil.getOutFilePath();

        FileUtil.writeToFile(Integer.parseInt(inputValue), filePath);

        System.out.println("Writing to file is done in location : " + filePath);
    }


    @Deprecated
    private static String generateRandomString() {

        Random rand = new Random();

        String str = rand.ints(48, 123)

                .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))

                .limit(100)

                .mapToObj(c -> (char) c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)

                .toString();

        return str;
    }
}
