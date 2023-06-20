package io.bayrktlihn.template.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ApplicationProperties {

    private static ApplicationProperties INSTANCE;

    private static final String PROFILE_KEY = "bayrktlihn.profile";

    private final Properties properties;

    private ApplicationProperties() {
        properties = loadProperties();
    }

    public <T> T getValue(String key, Class<T> clazz) {
        return (T) properties.getProperty(key);
    }

    private Properties loadProperties() {
        final Properties properties = new Properties();

        String profile = System.getProperty(PROFILE_KEY);

        Path path = PathUtil.getPathFromClasspath("application.properties");

        try {
            if (path != null) {
                properties.load(Files.newInputStream(path));
                if (profile == null) {
                    profile = properties.getProperty(PROFILE_KEY);
                }
            }

            if (profile != null) {
                Path profilePath = PathUtil.getPathFromClasspath("application-" + profile + ".properties");
                if (profilePath != null) {
                    properties.load(Files.newInputStream(profilePath));
                }
            }

            if (profile != null) {
                properties.put(PROFILE_KEY, profile);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }


    public static ApplicationProperties get() {
        if (INSTANCE == null) {
            synchronized (ApplicationProperties.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ApplicationProperties();
                }
            }
        }
        return INSTANCE;
    }


}
