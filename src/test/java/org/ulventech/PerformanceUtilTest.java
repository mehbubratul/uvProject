package org.ulventech;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerformanceUtilTest {

    private static final int MAX_INT_ACCEPTED_VALUE = (int) (Math.pow(2, 30) - 1);

    @Test
    void givenMaxAcceptedIntValueAsInputNumberCanPerformUsing2MBOfMemory_ReturnTrue() {
        // given
        byte[] bytes = RandomStringGenerationUtil.getInitialBytes();
        int inputNumber = MAX_INT_ACCEPTED_VALUE;

        // when
        long expectedMemoryInMegabytes = 2;

        // then
        PerformanceUtil.initializationOfPerformanceMeasurement();

        for (int i = 0; i < inputNumber; i++) {
            bytes = RandomStringGenerationUtil.getConsecutiveBytes(bytes);
        }

        long usedMemoryInMegabytes = PerformanceUtil.endMeasuringMemoryPerformance();
        assertTrue(expectedMemoryInMegabytes >= usedMemoryInMegabytes);
    }

    @Test
    void givenMaxAcceptedIntValueAsInputNumberCanPerformIn2Seconds_ReturnTrue() {
        // given
        byte[] bytes = RandomStringGenerationUtil.getInitialBytes();
        int inputNumber = MAX_INT_ACCEPTED_VALUE;

        // when
        long expectedTimeElapsedInSeconds = 2;
        // then
        PerformanceUtil.initializationOfPerformanceMeasurement();

        for (int i = 0; i < inputNumber; i++) {
            bytes = RandomStringGenerationUtil.getConsecutiveBytes(bytes);
        }

        long timeElapsedInSecond = PerformanceUtil.endMeasuringTimePerformance();

        assertTrue(timeElapsedInSecond <= expectedTimeElapsedInSeconds);
    }

    /** target : 1073741823
     * inputNumber-bytes-megabytes-executionTime
     * 100000000-3034776 Bytes-2M
     * 500000000-3034480 Bytes-2M
     * 1073741823-3035008 Bytes-2M-3834 Sec ~ 64 mins
     */
    @Test
    void givenMaxAcceptedIntValueAsInputNumberCanPerformWithin10MBOfMemory_ReturnTrue() {
        // given
        byte[] bytes = RandomStringGenerationUtil.getInitialBytes();
                        //1073741823
        int inputNumber = 1073741823;

        // when
        long expectedMemoryInMegabytes = 10;

        // then
        PerformanceUtil.initializationOfPerformanceMeasurement();

        FileUtil.writeToFile(inputNumber, FileUtil.getOutFilePath());

        long usedMemoryInMegabytes = PerformanceUtil.endMeasuringMemoryPerformance();
        long timeElapsedInSecond = PerformanceUtil.endMeasuringTimePerformance();
        System.out.println(timeElapsedInSecond);
        assertTrue(expectedMemoryInMegabytes >= usedMemoryInMegabytes);
    }


}