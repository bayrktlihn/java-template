package io.bayrktlihn.template.util.hashing;

public interface Hashing {
    String hash(String plainText);

    boolean check(String plainText, String hashedBase64Encoded);
}
