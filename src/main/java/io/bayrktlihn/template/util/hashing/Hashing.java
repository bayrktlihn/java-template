package io.bayrktlihn.template.util.hashing;

import io.bayrktlihn.template.util.hashing.impl.Md5;
import io.bayrktlihn.template.util.hashing.impl.Sha256;

public interface Hashing {
    String hash(String plainText);

    boolean check(String plainText, String hashedBase64Encoded);

    static Hashing createSha256() {
        return new Sha256();
    }

    static Hashing createMd5() {
        return new Md5();
    }
}
