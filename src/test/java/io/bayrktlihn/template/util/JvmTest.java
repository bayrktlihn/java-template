package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Test;

import java.util.Map;

class JvmTest {

    @Test
    void getPassedSystemProperties() {
        Map<String, String> passedSystemProperties = Jvm.getPassedSystemProperties();
        passedSystemProperties.forEach((k, v) -> {
            System.out.println(k + " "+v);
        });
    }

}