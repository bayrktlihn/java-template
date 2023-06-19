package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

class FileUtilTest {

    @Test
    void readAll() {
        Path path = PathUtil.getPathFromClasspath("holidays_in_every_year_tr.json");
        String fileContent = FileUtil.readAll(path.toFile());
        System.out.println(fileContent);
    }

}