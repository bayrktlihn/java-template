package io.bayrktlihn.template.util;

import io.bayrktlihn.template.TemplateApplication;

import java.net.URISyntaxException;
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

    public static Path getPathFromApplicationPath(String pathStr){
        try {
            Path path = Paths.get(TemplateApplication.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            return path.resolve("../" + pathStr).normalize();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
