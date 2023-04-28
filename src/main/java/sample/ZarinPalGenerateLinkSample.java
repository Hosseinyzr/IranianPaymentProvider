package sample;

import model.enumfiles.FinancialProviderType;
import model.zarinpal.ZarinPalConfig;
import service.FinancialFactory;

import static sample.ZarinPalConstants.DEFAULT_CALLBACK_URL;
import static sample.ZarinPalConstants.MERCHANT_ID;

public class ZarinPalGenerateLinkSample {

    public static void main(String[] args) throws Exception {
        ZarinPalConfig zarinPalConfig = ZarinPalConfig.builder()
                .merchantId(MERCHANT_ID)
                .defaultCallBackUrl(DEFAULT_CALLBACK_URL)
                .build();

        FinancialFactory financialFactory = new FinancialFactory(null, zarinPalConfig);

        //generating zarinpal financial handler
        var financial = financialFactory.generateFinancial(FinancialProviderType.ZARINPAL);

        var purchaseLinkResponseBean = financial.getPaymentInitialData(
                10000,
                null,
                null, //phone number of customer - it helps to provide registered cards for that number in payment gateway
                "45786",
                "test purchase",
                null,
                null // to restrict the card number for purchase
        );

        if (purchaseLinkResponseBean.getResultCode() != 0) {
            //handle unsuccessful operation
            var errorList = purchaseLinkResponseBean.getErrors();
            var message = purchaseLinkResponseBean.getMessage();
            //...
            return;
        }

        var token = purchaseLinkResponseBean.getToken();
        //update transaction record with token, so that later can fetch when call back is called

        //link of purchase that should send to user
        var linkOfPurchase = financial.getLinkOfPurchase(token);


    }
}
