package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class PathUtilTest {

    @Test
    void getPathFromApplicationPath() throws IOException {
        Path pathFromApplicationPath = PathUtil.getPathFromApplicationPath("/deneme.txt");

        if(Files.notExists(pathFromApplicationPath)){
            Files.createFile(pathFromApplicationPath);
        }

        Assertions.assertTrue(Files.exists(pathFromApplicationPath));
    }

    @Test
    void test() {
        Path path = Paths.get("/");
        Assertions.assertEquals(0, path.getNameCount());
        Assertions.assertNull( path.getFileName());

        path = Paths.get("/Users/bayrktlihn/..");
        Assertions.assertEquals(3, path.getNameCount());
        Assertions.assertEquals("Users", path.getName(0).toString());
        Assertions.assertEquals("bayrktlihn", path.getName(1).toString());
        Assertions.assertEquals("..", path.getName(2).toString());



        Assertions.assertNull(Paths.get(".").getRoot());
        Assertions.assertEquals(FileSystems.getDefault().getSeparator(), Paths.get("/Users").getRoot().toString());

        Path firstPath = Paths.get("/a/b");
        Path secondPath = Paths.get("/c/d");
        Assertions.assertEquals(Paths.get("/c/d"), firstPath.resolve(secondPath));

        secondPath = Paths.get("c/d");
        Assertions.assertEquals(Paths.get("/a/b/c/d"), firstPath.resolve(secondPath));

        firstPath = Paths.get("a/b");

        // a/b iken c/d ye nasıl giderim ../../c/d
        // her ikisi relative veya absolute olması gerekiyor
        System.out.println(firstPath.relativize(secondPath));
    }



}