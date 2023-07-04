package io.bayrktlihn.template.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtil {

    public static String readAll(File file) {
        try(BufferedReader bufferedReader = Files.newBufferedReader(file.toPath())) {

            StringBuffer stringBuffer = new StringBuffer();

            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuffer.append(line + System.lineSeparator());
                line = bufferedReader.readLine();
            }

            return stringBuffer.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
