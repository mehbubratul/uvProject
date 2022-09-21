package org.ulventech;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Since, the last two test cases are taking max accepted number as input,
 * It's recommended to have large memory space (100 GB for each test case).
 * Moreover, both of the test cases will require enough time ( 20 minutes around , in my system configuration).
 */
class PerformanceMeasurementServiceTest {

    private static final int MAX_INT_ACCEPTED_VALUE = (int) (Math.pow(2, 30) - 1);

    @Test
    void givenMinAcceptedIntValueAsInputNumberCanPerformIn10Seconds_ReturnTrue() {
        // given

        int inputNumber = 1;

        // when
        long expectedTimeElapsedInSeconds = 10;
        // then
        PerformanceMeasurementService.initializationOfPerformanceMeasurement();

        FileService.writeContentToFile(inputNumber, FileService.getOutFilePath());

        long timeElapsedInSecond = PerformanceMeasurementService.endMeasuringTimePerformance();

        assertTrue(timeElapsedInSecond <= expectedTimeElapsedInSeconds);
    }

    @Test
    void givenMinAcceptedIntValueAsInputNumberCanPerformUsing3MBOfMemory_ReturnTrue() {
        // given
        int inputNumber = 1;

        // when
        long expectedMemoryInMegabytes = 3;

        // then
        PerformanceMeasurementService.initializationOfPerformanceMeasurement();

        FileService.writeContentToFile(inputNumber, FileService.getOutFilePath());

        long usedMemoryInMegabytes = PerformanceMeasurementService.endMeasuringMemoryPerformance();

        assertTrue(expectedMemoryInMegabytes >= usedMemoryInMegabytes);
    }


    /**
     * This test case will take 1073741823 as input number;
     * This is max accepted number according to the problem statement
     * This method will print 1073741823 numbers of line in file; where each line is 100 char length.
     * The file size will be more than 100 GB.
     * Alert : This will take long execution time ( in my os, it takes around than 20 minutes most of the time)
     */
    @Test
    void givenMaxAcceptedIntValueAsInputNumberCanPerformUsing10MBOfMemory_ReturnTrue() {
        // given
        int inputNumber = MAX_INT_ACCEPTED_VALUE;

        // when
        long expectedMemoryInMegabytes = 10;

        // then
        PerformanceMeasurementService.initializationOfPerformanceMeasurement();

        FileService.writeContentToFile(inputNumber, FileService.getOutFilePath());

        long usedMemoryInMegabytes = PerformanceMeasurementService.endMeasuringMemoryPerformance();

        assertTrue(expectedMemoryInMegabytes >= usedMemoryInMegabytes);
    }

    /**
     * This test case will take 1073741823 as input number;
     * This is Max accepted number according to the problem statement
     * This method will print 1073741823 numbers of line in file; where each line is 100 char length.
     * The file size will be more than 100 GB.
     * Alert : This will take long execution time ( in my os, it takes around than 20 minutes most of the time)
     */
    @Test
    void givenMaxAcceptedIntValueAsInputNumberCanPerformIn1500Seconds_ReturnTrue() {
        // given

        int inputNumber = MAX_INT_ACCEPTED_VALUE;

        // when
        long expectedTimeElapsedInSeconds = 60 * 25;
        // then
        PerformanceMeasurementService.initializationOfPerformanceMeasurement();

        FileService.writeContentToFile(inputNumber, FileService.getOutFilePath());

        long timeElapsedInSecond = PerformanceMeasurementService.endMeasuringTimePerformance();

        assertTrue(timeElapsedInSecond <= expectedTimeElapsedInSeconds);
    }
}