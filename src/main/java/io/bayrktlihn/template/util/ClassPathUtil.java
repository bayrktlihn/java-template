package io.bayrktlihn.template.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ClassPathUtil {

    public static InputStream getInputStream(String name) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        return contextClassLoader.getResourceAsStream(name);
    }

    public static String getFileContent(String path) {
        try (
                InputStream inputStream = getInputStream(path);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
