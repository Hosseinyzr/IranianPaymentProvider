package sample;

import model.enumfiles.FinancialProviderType;
import model.zarinpal.ZarinPalConfig;
import service.FinancialFactory;

import static sample.ZarinPalConstants.DEFAULT_CALLBACK_URL;
import static sample.ZarinPalConstants.MERCHANT_ID;

public class ZarinPalCallBackProccessingSample {

    public static void main(String[] args) throws Exception {
        ZarinPalConfig zarinPalConfig = ZarinPalConfig.builder()
                .merchantId(MERCHANT_ID)
                .defaultCallBackUrl(DEFAULT_CALLBACK_URL)
                .build();

        FinancialFactory financialFactory = new FinancialFactory(null, zarinPalConfig);

        //generating zarinpal financial handler
        var financial = financialFactory.generateFinancial(FinancialProviderType.ZARINPAL);

        //https://domain/purchaseCallBack?Status=ok&Authority=A00000000000000000000000000426334543
        //token is received by callback url params and if status is 'ok' you should fetch the transaction data from your db by token
        String token = "A00000000000000000000000000426334543";
        Integer tomanAmount = 10000; //fetched from db

        var verifyResponseBean = financial.verifyTransaction(token, tomanAmount);
        if (verifyResponseBean.getResultCode()!=0){
            //handle error
           var errorList= verifyResponseBean.getErrors();
           var message= verifyResponseBean.getMessage();
           return;
        }

        //successful transaction. get the user what he wants! adding credit, etc.



    }
}
