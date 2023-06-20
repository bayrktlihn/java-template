package io.bayrktlihn.template.util.encryption.impl;

import io.bayrktlihn.template.util.ApplicationProperties;
import io.bayrktlihn.template.util.encryption.Encryption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

class RsaTest {
    @Test
    void decrypt() {
        ApplicationProperties applicationProperties = ApplicationProperties.get();
        String base64EncodedPrivateKey = applicationProperties.getValue("rsa.private_key", String.class);
        String base64EncodedPublicKey = applicationProperties.getValue("rsa.public_key", String.class);
        Encryption rsa = Encryption.createRsa(base64EncodedPrivateKey, base64EncodedPublicKey);

        String toBeEncrypted = "$ğüşiöç";

        String encrypted = rsa.encrypt(toBeEncrypted);
        String decrypted = rsa.decrypt(encrypted);

        Assertions.assertEquals(toBeEncrypted, decrypted);
    }

    @Test
    void generateKeyPair() {
        KeyPair keyPair = Rsa.generateKeyPair(2048);
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        byte[] encodedPrivateKey = privateKey.getEncoded();
        byte[] encodedPublicKey = publicKey.getEncoded();

        String base64EncodedPublicKey = Base64.getEncoder().encodeToString(encodedPublicKey);
        String base64EncodedPrivateKey = Base64.getEncoder().encodeToString(encodedPrivateKey);

        System.out.println(base64EncodedPrivateKey);
        System.out.println(base64EncodedPublicKey);
    }
}