package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomizerTest {

    @Test
    void generateRandomByDateTime(){
        String s = Randomizer.generateByDateTime();

        System.out.println(s);
    }
}