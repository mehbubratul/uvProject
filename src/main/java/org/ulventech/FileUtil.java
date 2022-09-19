package org.ulventech;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

public class FileUtil {

    public static String getOutFilePath() {

        String outFileName = "uvTech.txt";
        StringBuilder sb = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        sb.append("\\src\\main\\resources\\");
        sb.append(outFileName);

        return sb.toString();
    }

    public static boolean writeContentToFile(int numberOfLinesToBeWritten, String path) {

        //region validation

        if (numberOfLinesToBeWritten <= 0) return false;

        if (new StringUtil().isNullOrEmptyOrBlank(path)) {
            System.out.println("File path can not be empty");
            return false;
        }

        //endregion

        //region splitting number of lines input to reduce number of write operation

        //Here, we will divide x by 4 , so that 4 lines will be put into byte buffer; then, write to file

        int numberOfIteration = 4;

        int quotient = numberOfLinesToBeWritten / numberOfIteration;
        int remainder = numberOfLinesToBeWritten % numberOfIteration;

        int numberOfByteBufferIterationRequired;

        if (quotient == 0) { // remainder > 0
            numberOfByteBufferIterationRequired = 1;
        } else  { // if (quotient > 0) && remainder => 0
            if (remainder == 0) {
                numberOfByteBufferIterationRequired = quotient;
            } else {
                numberOfByteBufferIterationRequired = quotient + 1;
            }
        }
        //endregion

        //region Byte Buffer initialization

        int perByteArraySize = 102;

        int byteBufferSize = perByteArraySize * numberOfIteration;

        ByteBuffer buf = ByteBuffer.wrap(new byte[byteBufferSize]);
        //endregion

        //region Create first Bytes Array
        byte[] tempBytes = RandomStringGenerationUtil.getInitialBytes();
        //endregion

        try (FileChannel outputChannel = new FileOutputStream(FileUtil.getOutFilePath()).getChannel()) {

            for (int i = 0; i < numberOfByteBufferIterationRequired; i++) {

                buf.clear();

                for (int j = 0; j < numberOfIteration; j++) {

                    if (remainder != 0 && numberOfByteBufferIterationRequired == i + 1) {
                        numberOfIteration = remainder;
                    }

                    tempBytes = RandomStringGenerationUtil.getConsecutiveBytes(tempBytes);

                    if (buf.remaining() >= perByteArraySize) {
                        buf.put(tempBytes);
                        buf.put(new byte[]{'\r', '\n'});
                    }

                }

                buf.flip();

                outputChannel.write(buf);

            }

        } catch (FileNotFoundException e) {
            System.out.println("Unable to create / write file." + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Unable to write Content To File." + e.getMessage());
            return false;
        }

        buf.clear();

        return true;
    }

}
