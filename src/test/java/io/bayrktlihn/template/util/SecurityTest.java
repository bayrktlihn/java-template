package io.bayrktlihn.template.util;

import org.junit.jupiter.api.Test;

import java.security.Provider;
import java.security.Security;

class SecurityTest {

    @Test
    void test() {
        Provider[] providers = Security.getProviders();

        for (Provider provider : providers) {
            System.out.println(provider.getName() + " " + provider.getVersion() + " " + provider.getInfo());
            for (Provider.Service service : provider.getServices()) {
                System.out.println("\t" + service.getAlgorithm() + " " + service.getType());
            }
        }
    }

}
