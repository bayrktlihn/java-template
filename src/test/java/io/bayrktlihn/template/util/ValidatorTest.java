package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidatorTest {


    @Test
    void validateTurkishVehiclePlate() {
        boolean result = Validator.validateTurkishVehiclePlate("01 FF 494");
        Assertions.assertTrue(result);
    }

    @Test
    void validateInvalidTurkishVehiclePlate(){
        boolean result = Validator.validateTurkishVehiclePlate("00 FF 494");
        Assertions.assertFalse(result);
    }

}