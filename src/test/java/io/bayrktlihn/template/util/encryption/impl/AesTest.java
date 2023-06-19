package io.bayrktlihn.template.util.encryption.impl;

import io.bayrktlihn.template.util.ApplicationProperties;
import io.bayrktlihn.template.util.encryption.Encryption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Properties;

class AesTest {

    @Test
    void decrypt() {

        Properties properties = ApplicationProperties.getProperties();
        String aesKey = properties.getProperty("aes.key");
        Encryption aes = Encryption.createAes(aesKey);

        String toBeEncryptedPlainText = "üğüğüşlşiişşiçöçöçöç.";

        String encrypted = aes.encrypt(toBeEncryptedPlainText);
        String decrypted = aes.decrypt(encrypted);

        Assertions.assertEquals(toBeEncryptedPlainText, decrypted);

    }

    @Test
    void generateKey() {
        String generateBase64EncodedKey = Aes.generateBase64EncodedKey(256);
        System.out.println(generateBase64EncodedKey);
    }

}