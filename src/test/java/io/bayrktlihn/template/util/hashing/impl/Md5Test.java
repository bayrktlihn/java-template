package io.bayrktlihn.template.util.hashing.impl;

import io.bayrktlihn.template.util.hashing.Hashing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Md5Test {

    @Test
    void hash() {
        Hashing md5 = Hashing.createMd5();
        String hashed = md5.hash("alihan");
        System.out.println(hashed);
//        3Ns5JepLaEq3PlI2PnhPHQ==
    }

    @Test
    void check() {
        Hashing md5 = Hashing.createMd5();
        Assertions.assertTrue(md5.check("alihan", "3Ns5JepLaEq3PlI2PnhPHQ=="));
    }

}