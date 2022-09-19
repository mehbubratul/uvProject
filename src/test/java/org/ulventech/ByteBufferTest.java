package org.ulventech;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferTest {

    @Test
    void basics() {
        // Create a byte array
        byte[] bytes = new byte[10];

        // Wrap a byte array into a buffer
        ByteBuffer buf = ByteBuffer.wrap(bytes);

        // Retrieve bytes between the position and limit
        // (see Putting Bytes into a ByteBuffer)
        bytes = new byte[buf.remaining()];

        // transfer bytes from this buffer into the given destination array
        buf.get(bytes, 0, bytes.length);

        // Retrieve all bytes in the buffer
        buf.clear();
        bytes = new byte[buf.capacity()];

        // transfer bytes from this buffer into the given destination array
        buf.get(bytes, 0, bytes.length);
    }

    /**
     * target : 1073741823
     * inputNumber-bytes-megabytes-executionTime
     * 1073741823-26533952 Bytes-25 M - x 1417 ~ x mins
     * 1073741823-72680016 Bytes-69 M - x 1309 ~
     * ---------------------------
     * numberOfLinesToBeWritten = 1073741823
     * numberOfByteBufferIterationRequired = 1073741823 /2
     * Used memory is bytes: 14154760
     * Used memory is megabytes: 13
     * Execution time in nanoseconds: 1559650931800
     * Execution time in seconds: 1559
     * ---------------------------
     * <p>
     * Used memory is bytes: 18188064
     * Used memory is megabytes: 17
     * Execution time in nanoseconds: 454120225200
     * Execution time in seconds: 454
     */
    @Test
    void writeContentToFile() {

        PerformanceUtil.initializationOfPerformanceMeasurement();

        int numberOfLinesToBeWritten = 1073741823; //agr

        int numberOfIteration = 4;

        int numberOfByteBufferIterationRequired = (numberOfLinesToBeWritten / numberOfIteration) + 1;

        boolean isDone = false;

        int perByteArraySize = 102;

        int byteBufferSize = perByteArraySize * numberOfIteration;

        ByteBuffer buf = ByteBuffer.wrap(new byte[byteBufferSize]);

        byte[] tempBytes = RandomStringGenerationUtil.getInitialBytes();

        try (FileChannel outputChannel = new FileOutputStream(FileUtil.getOutFilePath()).getChannel()) {

            for (int i = 0; i < numberOfByteBufferIterationRequired; i++) {

                buf.clear();

                for (int j = 0; j < numberOfIteration; j++) {

                    tempBytes = RandomStringGenerationUtil.getConsecutiveBytes(tempBytes);

                    if (buf.remaining() >= perByteArraySize) {
                        buf.put(tempBytes);
                        buf.put(new byte[]{'\r', '\n'});
                    }

                }

                buf.flip();

                outputChannel.write(buf);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        buf.clear();
        PerformanceUtil.endMeasuringMemoryPerformance();
        PerformanceUtil.endMeasuringTimePerformance();
    }

    @Deprecated
    ByteBuffer bytesArrayToByteBuffer(byte[] tempBytes, ByteBuffer buf, int numberOfIteration, int perByteArraySize) {
        for (int i = 0; i < numberOfIteration; i++) {

            tempBytes = RandomStringGenerationUtil.getConsecutiveBytes(tempBytes);

            if (buf.remaining() >= perByteArraySize) {
                buf.put(tempBytes);
                buf.put(new byte[]{'\r', '\n'});
            }
        }
        return buf;
    }

    @Deprecated
    ByteBuffer bytesArrayToByteBuffer(byte[] tempBytes) {
        int perByteArraySize = 102;
        int numberOfIteration = 4;

        int byteBufferSize = perByteArraySize * numberOfIteration;

        ByteBuffer buf = ByteBuffer.wrap(new byte[byteBufferSize]);


        for (int i = 0; i < numberOfIteration; i++) {

            tempBytes = RandomStringGenerationUtil.getConsecutiveBytes(tempBytes);

            if (buf.remaining() >= perByteArraySize) {
                // System.out.println("Capacity:" + buf.capacity() + " - Remaining:" + buf.remaining());
                buf.put(tempBytes);
                buf.put(new byte[]{'\r', '\n'});
            } else {
                return buf;
            }

        }

        // System.out.println("Capacity:" + buf.capacity() + " - Remaining:" + buf.remaining());
        return buf;
    }
}
