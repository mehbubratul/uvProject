package org.ulventech;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.stream.Stream;

/**
 * important reading:
 * 1. Java Large Files – Efficient Processing
 * Using FileChannel
 * https://www.amitph.com/java-read-write-large-files-efficiently/
 * 2. Convert between ByteBuffer and byte array
 * https://examples.javacodegeeks.com/core-java/nio/bytebuffer/convert-between-bytebuffer-and-byte-array/
 * 3. Guide to ByteBuffer
 * https://www.baeldung.com/java-bytebuffer
 */
public class StreamTest {


    private void usingChannel() throws IOException {

        try (
                // FileChannel inputChannel = new FileInputStream(source).getChannel();
                FileChannel outputChannel = new FileOutputStream(FileUtil.getOutFilePath()).getChannel();
        ) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(4 * 1024);
            //while (inputChannel.read(buffer) != -1) {
            while (true) {
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();
            }
        }
    }

    private void usingJavaStreams() throws IOException {
        // given
        int inputNumber = 2; //1073741823;

        try (
                InputStream inputStream = new FileInputStream(getRandomBytes(2));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                FileWriter fileWriter = new FileWriter(FileUtil.getOutFilePath(), true);
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));
                Stream<String> linesStream = bufferedReader.lines();
        ) {
            linesStream
                    .forEach(printWriter::println);
        }
    }

    private String getRandomBytes(int numberOfLine) {
        byte[] bytes = RandomStringGenerationUtil.getInitialBytes();
        for (int i = 0; i < numberOfLine; i++) {
            bytes = RandomStringGenerationUtil.getConsecutiveBytes(bytes);
        }
        return "";
    }
}
