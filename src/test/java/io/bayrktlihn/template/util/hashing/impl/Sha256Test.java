package io.bayrktlihn.template.util.hashing.impl;

import io.bayrktlihn.template.util.hashing.Hashing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Sha256Test {

    @Test
    void hash() {
        Hashing sha256 = Hashing.createSha256();

        String hashed = sha256.hash("öçşiğü");

        System.out.println(hashed);
        //eHpNxTr19x8SihYxY+DP0/xLYwOwqNytldgZRo3SmBA=
    }

    @Test
    void check() {
        Hashing sha256 = Hashing.createSha256();

        boolean result = sha256.check("öçşiğü", "eHpNxTr19x8SihYxY+DP0/xLYwOwqNytldgZRo3SmBA=");

        Assertions.assertTrue(result);
    }

}