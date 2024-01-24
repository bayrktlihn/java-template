package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Test;

import java.util.Base64;

class BarcodeGeneratorTest {
    @Test
    void createQRImage() {
        try {
            byte[] qrImage = BarcodeGenerator.createQRImage("bayrktlihn", 150, "png");
            System.out.println(Base64.getEncoder().encodeToString(qrImage));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}