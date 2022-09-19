package org.ulventech;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerformanceMeasurementServiceTest {

    private static final int MAX_INT_ACCEPTED_VALUE = (int) (Math.pow(2, 30) - 1);

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