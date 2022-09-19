package org.ulventech;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertFalse;

class UVTechAppTest {

    @BeforeEach
    void setUp() {
        // given
        // when
        // then
    }

    //region valid test cases
    @Test
    void givenFilePathAndLineNumberWillReturnTrue() {
        // given
        int number = 10;
        // when
        boolean isDone = FileUtil.writeToFile(number, FileUtil.getOutFilePath());
        // then
        assertTrue(isDone);
    }

    @Test
    void givenFilePathAndLineNumberIsZeroWillReturnFalse() {
        // given
        int number = 0;
        // when
        boolean isDone = FileUtil.writeToFile(number, FileUtil.getOutFilePath());
        // then
        assertFalse(isDone);
    }

    @Test
    void givenNullOrEmptyAsFilePathAndLineNumberIsZeroWillReturnFalse() {
        // given
        int number = 0;
        // when
        boolean isDone = FileUtil.writeToFile(number, null);
        // then
        assertFalse(isDone);
    }
    //endregion

    //region SandBox Test Code
    @Test
    void test1() {

        //1073741823; 1,07,37,41,823
        int x = 1073741823;
        int dividedBy = 10;
        int y = x / dividedBy;

        byte[] bytes = RandomStringGenerationUtil.getInitialBytes();

        PerformanceUtil.initializationOfPerformanceMeasurement();
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(FileUtil.getOutFilePath()))) {
            bytes = extracted(1, y, bytes, bufferedOutputStream);

            bytes = extracted(y, (2 * y), bytes, bufferedOutputStream);

            bytes = extracted((2 * y), (3 * y), bytes, bufferedOutputStream);

            bytes = extracted((3 * y), (4 * y), bytes, bufferedOutputStream);

            bytes = extracted((4 * y), (5 * y), bytes, bufferedOutputStream);

            bytes = extracted((5 * y), (6 * y), bytes, bufferedOutputStream);

            bytes = extracted((6 * y), (7 * y), bytes, bufferedOutputStream);

            bytes = extracted((7 * y), (8 * y), bytes, bufferedOutputStream);

            bytes = extracted((8 * y), (9 * y), bytes, bufferedOutputStream);

            bytes = extracted((9 * y), x, bytes, bufferedOutputStream);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PerformanceUtil.endMeasuringMemoryPerformance();
    }

    private byte[] extracted(int startPos, int endPos, byte[] bytes, BufferedOutputStream bufferedOutputStream) throws IOException {
        byte[] crlf = new byte[]{'\r', '\n'};
        for (int i = startPos; i < endPos; i++) {
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.write(crlf);
            bufferedOutputStream.flush();
            bytes = RandomStringGenerationUtil.getConsecutiveBytes(bytes);
        }
        return bytes;
    }

    @Test
    void Test2() {
        PerformanceUtil.initializationOfPerformanceMeasurement();
        StringBuffer stringBuffer = new StringBuffer(1630);
        byte[] bytes = RandomStringGenerationUtil.getInitialBytes();
        byte[] crlf = new byte[]{'\r', '\n'};
        int len = 1073741823 / 64;
        for (long i = 0; i < 16; i++) {
            stringBuffer.append(new String(bytes, StandardCharsets.UTF_8));
            stringBuffer.append(System.lineSeparator());
        }
        System.out.println(stringBuffer.capacity());
        PerformanceUtil.endMeasuringMemoryPerformance();
        System.out.println(stringBuffer);
    }

    @Test
    void Test3() {
        PerformanceUtil.initializationOfPerformanceMeasurement();
        writeIntoFile();
        PerformanceUtil.endMeasuringMemoryPerformance();
    }

    private StringBuffer getStringBufferFromByteArray() {

        StringBuffer stringBuffer = new StringBuffer(1630);

        for (int i = 0; i < 16; i++) {
            stringBuffer.append(new String(RandomStringGenerationUtil.getInitialBytes(), StandardCharsets.UTF_8));
            stringBuffer.append(System.lineSeparator());
        }

        return stringBuffer;
    }

    private void writeIntoFile() {
        String filePath = FileUtil.getOutFilePath();

        try (FileWriter writer = new FileWriter(filePath);
             BufferedWriter bw = new BufferedWriter(writer)) {
            int limit = 1073741823;
            int step = 16;
            int i = 0;
            for (; i < limit; ) {
                bw.write(getStringBufferFromByteArray().toString());
                //flush the stream
                bw.flush();
                i = i + step;
                System.out.println(i);
            }
            System.out.println(limit * 16);
            //close the stream
            bw.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void Test4() throws IOException {
        PerformanceUtil.initializationOfPerformanceMeasurement();

        String filePath = FileUtil.getOutFilePath();

        for (int i = 0; i < 2; i++) {
            try (FileWriter writer = new FileWriter(filePath);
                 BufferedWriter bw = new BufferedWriter(writer)) {
                bw.write(getStringBufferFromByteArray().toString());
                //flush the stream
                bw.flush();
                bw.close();
            }
        }

        PerformanceUtil.endMeasuringMemoryPerformance();
    }

    @Test
    void Test5() {

        PerformanceUtil.initializationOfPerformanceMeasurement();

        String filePath = FileUtil.getOutFilePath();
        for (int i = 0; i < 100000; i++) {
            try (FileWriter fw = new FileWriter(filePath, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {

                out.println(RandomStringGenerationUtil.getInitialBytes());

            } catch (IOException e) {
                // File writing/opening failed at some stage.
            }
        }
        PerformanceUtil.endMeasuringMemoryPerformance();
    }
    //endregion

}