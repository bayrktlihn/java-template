package io.bayrktlihn.template.dto.tcmb;

import io.bayrktlihn.template.util.XML;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TarihDateDtoTest {
    @Test
    void test() {
        TarihDateDto tarihDateDto = new TarihDateDto();


        ArrayList<CurrencyDto> currencies = new ArrayList<>();
        currencies.add(new CurrencyDto());
        currencies.add(new CurrencyDto());
        currencies.add(new CurrencyDto());

        tarihDateDto.setCurrencies(currencies);


        String stringify = XML.stringify(tarihDateDto);

        System.out.println(stringify);
    }
}