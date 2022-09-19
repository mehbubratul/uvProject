package org.ulventech;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class ByteBufferTest {
    /*
    Create a byte array and wrap it into a ByteBuffer.
        The buffer’s capacity and limit will be the array’s length and its position will be zero.
    Retrieve the bytes between the current position and the limit of the buffer.
        The new byte array’s length is set to the number of remaining elements in the buffer,
        using the remaining() API method and then the bytes are transfered from the buffer to the byte array,
        using the get(byte[] dst, int offset, int length) API method.
    Retrieve all bytes in the buffer.
        First the buffer position is set to 0 and the buffer limit is set to its capacity,
            with the clear() API method,
            then the new byte array’s length is set to the buffer’s capacity and
            then again the get(byte[] dst, int offset, int length) API method transfers bytes
                from the buffer into the array.
     */
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

    /** target : 1073741823
     * inputNumber-bytes-megabytes-executionTime
     * 1073741823-26533952 Bytes-25 M - x 1417 ~ x mins
     *
     *
     */
    @Test
    void writeContentToFile() {
        PerformanceUtil.initializationOfPerformanceMeasurement();
        int numberOfLinesToBeWritten = 1073741823; //1073741823
        int numberOfByteBufferIterationRequired = numberOfLinesToBeWritten / 4;
        boolean isDone = false;

        byte[] tempBytes = RandomStringGenerationUtil.getInitialBytes();

        try (FileChannel outputChannel = new FileOutputStream(FileUtil.getOutFilePath()).getChannel()) {

            for (int i = 0; i < numberOfByteBufferIterationRequired; i++) {
                ByteBuffer buffer = bytesArrayToByteBuffer(tempBytes);
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();
            }


            //isDone = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //return isDone;
        }
        PerformanceUtil.endMeasuringMemoryPerformance();
        PerformanceUtil.endMeasuringTimePerformance();
    }


    ByteBuffer bytesArrayToByteBuffer(byte[] tempBytes) {
        int perByteArraySize = 102;
        int numberOfIteration = 4;

        int byteBufferSize = perByteArraySize * numberOfIteration;

        ByteBuffer buf = ByteBuffer.wrap(new byte[byteBufferSize]);

        for (int i = 0; i < numberOfIteration; i++) {

            if (i == 0) {
                buf.clear();
                buf.mark();
            }

            tempBytes = RandomStringGenerationUtil.getConsecutiveBytes(tempBytes);

            if (buf.remaining() >= perByteArraySize) {
                // System.out.println("Capacity:" + buf.capacity() + " - Remaining:" + buf.remaining());
                buf.put(tempBytes);
                buf.put(new byte[]{'\r', '\n'});
            } else {
                // System.out.println("java.nio.BufferOverflowException");
                //System.out.println("Original ByteBuffer:  " + Arrays.toString(buf.array()));
                buf.reset();
                //System.out.println("Original ByteBuffer:  " + Arrays.toString(buf.array()));
                return buf;
            }

            /*while (buf.hasRemaining()){
                System.out.println(buf.position() + " -> " + buf.get());
            }*/
        }

        // System.out.println("Capacity:" + buf.capacity() + " - Remaining:" + buf.remaining());
        return buf;
    }
}
