package io.bayrktlihn.template.util.hashing.impl;

import io.bayrktlihn.template.util.hashing.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Md5 implements Hashing {
    @Override
    public String hash(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashedBytes = md.digest(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean check(String plainText, String hashedBase64Encoded) {
        String hashed = hash(plainText);
        return hashed.equals(hashedBase64Encoded);
    }
}
