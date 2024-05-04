package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Test;

class ClassPathUtilTest {

    @Test
    void getFileContent() {
        String fileContent = ClassPathUtil.getFileContent("holidays_in_every_year_tr.json");
        System.out.println(fileContent);
    }

}