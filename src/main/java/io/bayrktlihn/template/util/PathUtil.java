package io.bayrktlihn.template.util;

import io.bayrktlihn.template.TemplateApplication;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.regex.Pattern;

public class PathUtil {


    public static Path getPathFromApplicationPath(String pathStr){

        pathStr = pathStr.replaceAll("/{2,}", "/");

        pathStr = pathStr.startsWith("/") ? pathStr.substring(1) : pathStr;

        try {
            Class<TemplateApplication> templateApplicationClass = TemplateApplication.class;
            ProtectionDomain protectionDomain = templateApplicationClass.getProtectionDomain();
            CodeSource codeSource = protectionDomain.getCodeSource();
            URI uri = codeSource.getLocation().toURI();
            Path path = Paths.get(uri);
            Path parent = path.getParent();
            return parent.resolve(pathStr).normalize().toAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
