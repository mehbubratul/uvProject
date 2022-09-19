package org.ulventech;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;

public class FileUtil {

    public static String getOutFilePath() {

        String outFileName = "uvTech.txt";
        StringBuilder sb = new StringBuilder(Paths.get("").toAbsolutePath().toString());
        sb.append("\\src\\main\\resources\\");
        sb.append(outFileName);

        return sb.toString();
    }

    public static boolean writeToFile(int x, String path) {
        if (x <= 0) return false;

        if (new StringUtil().isNullOrEmptyOrBlank(path)) {
            System.out.println("File path can not be empty");
            return false;
        }

        byte[] crlf = new byte[]{'\r', '\n'};
        boolean isDone = false;
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path))) {

            byte[] bytes = RandomStringGenerationUtil.getInitialBytes();

            for (int i = 0; i < x; i++) {

                bufferedOutputStream.write(bytes);
                bufferedOutputStream.write(crlf);
                bufferedOutputStream.flush();

                bytes = RandomStringGenerationUtil.getConsecutiveBytes(bytes);
            }
            isDone = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            return isDone;
        }

    }


}
