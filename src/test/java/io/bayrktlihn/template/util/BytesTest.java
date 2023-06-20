package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

class BytesTest {

    @Test
    void turkishCharTest() {
        byte[] name = "ü".getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(2, name.length);
    }

    @Test
    void chinaCharTest() {
        byte[] bytes = "国".getBytes(StandardCharsets.UTF_8);
        Assertions.assertEquals(3, bytes.length);
    }

    @Test
    void create() {
        byte[] bytes = Bytes.create(32);
        String encodedBytes = Base64.getEncoder().encodeToString(bytes);
        System.out.println(encodedBytes);
    }

}
