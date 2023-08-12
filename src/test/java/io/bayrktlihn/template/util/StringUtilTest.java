package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilTest {

    @Test
    void removeRepeatSpace() {
        String result = StringUtil.removeRepeatSpace("alihan                 bayraktar");
        Assertions.assertEquals("alihan bayraktar", result);
    }

    @Test
    void mask() {
        String cardNumber = "5454545454545454";
        String maskedAlihan = StringUtil.mask(cardNumber, 6, 2);
        Assertions.assertEquals("545454********54", maskedAlihan);
    }

}