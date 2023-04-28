package service;

import model.enumfiles.FinancialProviderType;
import model.zarinpal.ZarinPalConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FinancialFactoryTest {


    @Test
    void testZarinPal_mustFailForIncompleteData() throws Exception{

        Assertions.assertThrows(Exception.class,() -> {
            FinancialFactory financialFactory = new FinancialFactory(null, ZarinPalConfig.builder().merchantId("").build());
            var financial=financialFactory.generateFinancial(FinancialProviderType.ZARINPAL);
        },
                "messsageee");

    }
}