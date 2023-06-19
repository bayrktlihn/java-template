package io.bayrktlihn.template.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ApplicationProperties {

	private static final Properties PROPERTIES;
	private static final String PROFILE_KEY = "bayrktlihn.profile";

	static {
		PROPERTIES = new Properties();

		String profile = System.getProperty(PROFILE_KEY);

		Path path = PathUtil.getPathFromClasspath("application.properties");

		try {
			if (path != null) {
				PROPERTIES.load(Files.newInputStream(path));
				if (profile == null) {
					profile = PROPERTIES.getProperty(PROFILE_KEY);
				}
			}

			if (profile != null) {
				Path profilePath = PathUtil.getPathFromClasspath("application-" + profile + ".properties");
				if (profilePath != null) {
					PROPERTIES.load(Files.newInputStream(profilePath));
				}
			}

			if (profile != null) {
				PROPERTIES.put(PROFILE_KEY, profile);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static Properties getProperties() {
		return PROPERTIES;
	}

}
