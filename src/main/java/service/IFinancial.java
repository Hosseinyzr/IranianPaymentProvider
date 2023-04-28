package service;


import model.PurchaseLinkResponseBean;
import model.TransactionDataBean;
import model.VerifyResponseBean;
import model.enumfiles.FinancialProviderType;
import model.vandar.VSettlementResponse;

public interface IFinancial {

    static final Integer TomanToRialCurrency = 10;


    PurchaseLinkResponseBean getPaymentInitialData(Integer tomanAmount,
                                                   String callBackUrl,
                                                   String customerMobileNumber,
                                                   String factorNumber,
                                                   String description,
                                                   String nationalCode,
                                                   String validCardNumber);


    TransactionDataBean getTransactionData(String token);

    VerifyResponseBean verifyTransaction(String token, Integer tomanAmount) throws Exception;

    String getLinkOfPurchase(String token);

    VSettlementResponse transferMoney(Integer amount,
                                      String iban,
                                      String trackId,
                                      Integer paymentNumber,
                                      String notifyUrl,
                                      String description) throws Exception;

    FinancialProviderType getFinancialProviderType();

    String refreshToken() throws Exception;


}
