package org.ulventech;

public class PerformanceUtil {
    private static Runtime runtime;
    private static long startTime;
    private static final long MEGABYTE = 1024L * 1024L;

    public static void initializationOfPerformanceMeasurement() {
        // Get the Java runtime
        startTime = System.nanoTime();
        runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
    }

    public static long endMeasuringMemoryPerformance() {

        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        long usedMemoryInMegabytes = bytesToMegabytes(memory);

        // Display the Memory Result
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: " + usedMemoryInMegabytes);

        return usedMemoryInMegabytes;
    }

    public static long endMeasuringTimePerformance() {

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        long timeElapsedInSecond = timeElapsed / (1000000 * 1000);
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in seconds: " + timeElapsedInSecond);

        return timeElapsedInSecond;
    }

    private static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
}
