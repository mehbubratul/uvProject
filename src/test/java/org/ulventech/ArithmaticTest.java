package org.ulventech;

import org.junit.jupiter.api.Test;

public class ArithmaticTest {

    @Test
    void checkMath() {
        int numberOfLinesToBeWritten = 10;
        int numberOfIteration = 4;

        int numberOfByteBufferIterationRequired = (numberOfLinesToBeWritten / numberOfIteration) + 1;

        int quotient = numberOfLinesToBeWritten / numberOfIteration;
        int remainder = numberOfLinesToBeWritten % numberOfIteration;

        if (quotient == 0) { // remainder > 0
            numberOfByteBufferIterationRequired = 1;
        } else if (quotient > 0) { // remainder => 0
            if (remainder == 0) {
                numberOfByteBufferIterationRequired = quotient;
            } else {
                numberOfByteBufferIterationRequired = quotient + 1;
            }
        }

        System.out.println(quotient + "-" + remainder);

        for (int i = 0; i < numberOfByteBufferIterationRequired; i++) {
            System.out.println("quotant" + i);
            if (remainder != 0 && numberOfByteBufferIterationRequired == i + 1) {
                numberOfIteration = remainder;
            }
            for (int j = 0; j < numberOfIteration; j++) {
                System.out.println("remainder" + j);
            }
        }
    }
}
