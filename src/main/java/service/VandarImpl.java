package service;

import Util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.PurchaseLinkResponseBean;
import model.TransactionDataBean;
import model.VerifyResponseBean;
import model.enumfiles.FinancialProviderType;
import model.vandar.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static Util.JsonUtil.getObjectMapper;


public class VandarImpl implements IFinancial {


    private static final String INITIAL_URL = "https://ipg.vandar.io/api/v3/send";


    public static String API_KEY;
    public static final String VANDAR_START_PAY_URL = "https://ipg.vandar.io/v3/";
    private static final String TRANSACTION_URL = "https://ipg.vandar.io/api/v3/transaction";
    private static final String VERIFY_URL = "https://ipg.vandar.io/api/v3/verify";

    public static final String  REFRESH_TOKEN_URL = "https://api.vandar.io/v3/refreshtoken";

    public static String BUSINESS_NAME = ""; //developers
    private String defaultCallBackUrl = "";

    private static String accessToken = "";
    private static String refreshToken = "";



    private final static ObjectMapper mapper = getObjectMapper();

    public static final Logger logger = LoggerFactory.getLogger(VandarImpl.class);

    public static WebClient webClient;


    public VandarImpl(VandarConfig vandarConfig) throws Exception {
        initialConfigs(vandarConfig);
    }

    private void initialConfigs(VandarConfig vandarConfig) throws Exception {
        if (vandarConfig == null)
            throw new IllegalArgumentException("vandar config must not be null");

        if (
                StringUtil.isNulOrEmpty(vandarConfig.getAccessToken()) ||
                        StringUtil.isNulOrEmpty(vandarConfig.getRefreshToken()) ||
                        StringUtil.isNulOrEmpty(vandarConfig.getApiKey()) ||
                        StringUtil.isNulOrEmpty(vandarConfig.getBusinessName())
        ) {
            throw new IllegalArgumentException("vandar config is incomplete");
        }

        accessToken = vandarConfig.getAccessToken();
        refreshToken = vandarConfig.getRefreshToken();
        API_KEY = vandarConfig.getApiKey();
        BUSINESS_NAME = vandarConfig.getBusinessName();
        defaultCallBackUrl = vandarConfig.getDefaultCallBackUrl();

    }

    static {
        webClient = WebClient.create();
    }

    @Override
    public PurchaseLinkResponseBean getPaymentInitialData(Integer tomanAmount,
                                                          String callBackUrl,
                                                          String customerMobileNumber,
                                                          String factorNumber,
                                                          String description,
                                                          String nationalCode,
                                                          String validCardNumber) {


        VFirstCalRequest vFirstCalRequest = new VFirstCalRequest();
        vFirstCalRequest.setApi_key(API_KEY);
        vFirstCalRequest.setAmount(tomanAmount);

        callBackUrl = StringUtil.isNulOrEmpty(callBackUrl) ? defaultCallBackUrl : callBackUrl;
        if (StringUtil.isNulOrEmpty(callBackUrl))
            throw new IllegalArgumentException("call back url is not set");

        vFirstCalRequest.setCallback_url(callBackUrl);
        vFirstCalRequest.setDescription(description);
        vFirstCalRequest.setMobile_number(customerMobileNumber);
        vFirstCalRequest.setNational_code(nationalCode);
        vFirstCalRequest.setFactorNumber(factorNumber);
        vFirstCalRequest.setValid_card_number(validCardNumber);


        var response = webClient.post()
                .uri(INITIAL_URL)
                .bodyValue(vFirstCalRequest)
                .exchange()
                .flatMap(clientResponse -> {
                    //Error handling
                    if (clientResponse.statusCode().isError()) { // or clientResponse.statusCode().value() >= 400
                        return clientResponse.bodyToMono(String.class);

                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block();


        VDataFirst vDataFirst = null;
        try {
            vDataFirst = mapper.readValue(response, VDataFirst.class);

        } catch (IOException e) {
            return PurchaseLinkResponseBean.ofException(e.getMessage());
        }

        return PurchaseLinkResponseBean.ofVandar(vDataFirst);
    }

    @Override
    public TransactionDataBean getTransactionData(String token) {

        VTransactionDataRequest vTransactionDataRequest = new VTransactionDataRequest();
        vTransactionDataRequest.setApi_key(API_KEY);
        vTransactionDataRequest.setToken(token);


        var response = webClient.post()
                .uri(TRANSACTION_URL)
                .bodyValue(vTransactionDataRequest)
                .exchange()
                .flatMap(clientResponse -> {
                    //Error handling
                    if (clientResponse.statusCode().isError()) { // or clientResponse.statusCode().value() >= 400
                        return clientResponse.bodyToMono(String.class);

                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block();


        VTransactionDataResponse vTransactionDataResponse = null;
        try {
            vTransactionDataResponse = mapper.readValue(response, VTransactionDataResponse.class);

        } catch (IOException e) {
            return TransactionDataBean.ofException(e.getMessage());
        }

        return TransactionDataBean.ofVandar(vTransactionDataResponse);
    }

    @Override
    public VerifyResponseBean verifyTransaction(String token, Integer tomanAmount) throws Exception {

        VVerifyRequest vVerifyRequest = new VVerifyRequest();
        vVerifyRequest.setToken(token);
        vVerifyRequest.setApi_key(API_KEY);


        var response = webClient.post()
                .uri(VERIFY_URL)
                .bodyValue(vVerifyRequest)
                .exchange()
                .flatMap(clientResponse -> {
                    //Error handling
                    if (clientResponse.statusCode().isError()) { // or clientResponse.statusCode().value() >= 400
                        return clientResponse.bodyToMono(String.class);

                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block();

        VVerifyResponse vVerifyResponse = null;
        try {
            vVerifyResponse = mapper.readValue(response, VVerifyResponse.class);
        } catch (IOException e) {
            return VerifyResponseBean.ofException(e.getMessage());
        }


        VerifyResponseBean verifyResponseBean = VerifyResponseBean.ofVandar(vVerifyResponse);

        return verifyResponseBean;
    }

    @Override
    public String getLinkOfPurchase(String token) {
        return VANDAR_START_PAY_URL + token;
    }

    @Override
    public VSettlementResponse transferMoney(Integer amount,
                                             String iban,
                                             String trackId,
                                             Integer paymentNumber,
                                             String notifyUrl,
                                             String description) throws Exception {
        VSettlementRequest vSettlementRequest = new VSettlementRequest();
        vSettlementRequest.setAmount(amount);
        vSettlementRequest.setIban(iban);
        vSettlementRequest.setTrack_id(trackId);
        vSettlementRequest.setPayment_number(paymentNumber);
        vSettlementRequest.setNotify_url(notifyUrl);
        vSettlementRequest.setDescription(description);


        String settlementUrl = "https://api.vandar.io/v3/business/{business}/settlement/store";
        settlementUrl = settlementUrl.replace("{business}", BUSINESS_NAME);

        var response = webClient.post()
                .uri(settlementUrl)
                .bodyValue(vSettlementRequest)
                .exchange()
                .flatMap(clientResponse -> {
                    //Error handling
                    if (clientResponse.statusCode().isError()) { // or clientResponse.statusCode().value() >= 400
                        return clientResponse.bodyToMono(String.class);

                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block();

        VSettlementResponse vSettlementResponse = null;
        vSettlementResponse = mapper.readValue(response, VSettlementResponse.class);

        return vSettlementResponse;
    }

    @Override
    public FinancialProviderType getFinancialProviderType() {
        return FinancialProviderType.VANDAR;
    }

    @Override
    public String refreshToken() throws Exception {

        VRefreshTokenRequest vRefreshTokenRequest = new VRefreshTokenRequest();
        vRefreshTokenRequest.setRefreshtoken(refreshToken);

        var response = webClient.post()
                .uri(REFRESH_TOKEN_URL)
                .bodyValue(vRefreshTokenRequest)
                .exchange()
                .flatMap(clientResponse -> {
                    //Error handling
                    if (clientResponse.statusCode().isError()) { // or clientResponse.statusCode().value() >= 400
                        return clientResponse.bodyToMono(String.class);
                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block();

        VRefreshTokenResponse vRefreshTokenResponse = null;
        vRefreshTokenResponse = mapper.readValue(response, VRefreshTokenResponse.class);

        refreshToken = vRefreshTokenResponse.getRefresh_token();
        accessToken = vRefreshTokenResponse.getAccess_token();
        return vRefreshTokenResponse.getAccess_token();
    }


}
