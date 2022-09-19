package org.ulventech;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerformanceUtilTest {

    private static final int MAX_INT_ACCEPTED_VALUE = (int) (Math.pow(2, 30) - 1);

    @Test
    void givenMaxAcceptedIntValueAsInputNumberCanPerformUsing10MBOfMemory_ReturnTrue() {
        // given
        int inputNumber = MAX_INT_ACCEPTED_VALUE;

        // when
        long expectedMemoryInMegabytes = 10;

        // then
        PerformanceUtil.initializationOfPerformanceMeasurement();

        FileUtil.writeContentToFile(inputNumber, FileUtil.getOutFilePath());

        long usedMemoryInMegabytes = PerformanceUtil.endMeasuringMemoryPerformance();

        assertTrue(expectedMemoryInMegabytes >= usedMemoryInMegabytes);
    }

    @Test
    void givenMaxAcceptedIntValueAsInputNumberCanPerformIn1500Seconds_ReturnTrue() {
        // given

        int inputNumber = MAX_INT_ACCEPTED_VALUE;

        // when
        long expectedTimeElapsedInSeconds = 60 * 25;
        // then
        PerformanceUtil.initializationOfPerformanceMeasurement();

        FileUtil.writeContentToFile(inputNumber, FileUtil.getOutFilePath());

        long timeElapsedInSecond = PerformanceUtil.endMeasuringTimePerformance();

        assertTrue(timeElapsedInSecond <= expectedTimeElapsedInSeconds);
    }

}