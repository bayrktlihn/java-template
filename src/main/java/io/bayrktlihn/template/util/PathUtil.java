package io.bayrktlihn.template.util;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtil {

	public static Path getPathFromClasspath(String path) {

		try {

			URL url = Thread.currentThread().getContextClassLoader().getResource(path);

			return Paths.get(url.toURI());

		} catch (Exception e) {
			return null;
		}

	}

}
