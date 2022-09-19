package org.ulventech;

import java.util.Scanner;

public class ScanningService {
    public static String doScanningAndGetIntValue() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a number: ");
        String inputString = scan.nextLine();

        // Print the values to check if the input was correctly obtained.
        System.out.println("You entered : " + inputString);

        if ("END".equals(inputString)) { //a way to exit the loop
            System.exit(0);
        }
        return inputString;
    }
}
