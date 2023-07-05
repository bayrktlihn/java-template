package io.bayrktlihn.template.client;

import io.bayrktlihn.template.client.tcmb.TCMBClient;
import io.bayrktlihn.template.client.tcmb.dto.CurrencyDto;
import io.bayrktlihn.template.client.tcmb.dto.TarihDateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class TCMBClientTest {

    private TCMBClient tcmbClient;

    @BeforeEach
    void beforeEach() {
        tcmbClient = new TCMBClient();
    }

    @Test
    void test() {
        TarihDateDto tarihDate = tcmbClient.getCurrencies();


        List<CurrencyDto> currencies = tarihDate.getCurrencies();

        for (CurrencyDto currency : currencies) {
            System.out.println(currency.getCurrencyCode() + " " + currency.getCurrencyName() + " "+currency.getForexBuying());
        }

    }

}