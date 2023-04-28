package service;

import Util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.PurchaseLinkResponseBean;
import model.TransactionDataBean;
import model.VerifyResponseBean;
import model.enumfiles.FinancialProviderType;
import model.vandar.VSettlementResponse;
import model.zarinpal.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static Util.JsonUtil.getObjectMapper;


public class ZarinPalImpl implements IFinancial {


    public static final String ZARINPAL_START_URL = "https://www.zarinpal.com/pg/StartPay/";
    private static final String PAYMENT_INITIAL_URL = "https://api.zarinpal.com/pg/v4/payment/request.json";
    private static final String VERIFY_URL = "https://api.zarinpal.com/pg/v4/payment/verify.json";

    public static String MERCHANT_ID = "";

    private String defaultCallBackUrl = "";

    private final ObjectMapper mapper = getObjectMapper();


    public static final Logger logger = LoggerFactory.getLogger(ZarinPalImpl.class);

    public static WebClient webClient  /* = WebClient.create()*/;
    // static SslContext sslContext = null;

    public ZarinPalImpl(ZarinPalConfig zarinPalConfig) throws Exception {
        initialConfigs(zarinPalConfig);
    }

    private void initialConfigs(ZarinPalConfig zarinPalConfig) throws Exception {
        if (zarinPalConfig == null)
            throw new IllegalArgumentException("zarinpal config must not be null");


        if (StringUtil.isNulOrEmpty(zarinPalConfig.getMerchantId())) {
            throw new IllegalArgumentException("zarinpal config is incomplete");
        }

        MERCHANT_ID = zarinPalConfig.getMerchantId();
        defaultCallBackUrl = zarinPalConfig.getDefaultCallBackUrl();
    }

    static {

      /*  try {
            sslContext = SslContextBuilder
                    .forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build();


            HttpClient httpClient = HttpClient.create().secure(sslContextSpec -> sslContextSpec.sslContext(sslContext));
            webClient = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
        } catch (SSLException e) {
            e.printStackTrace();
            logger.error("Http Client", e);
        }*/
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

        ZFirstCalRequest zFirstCalRequest = new ZFirstCalRequest();

        //zarin pal needs Rial
        zFirstCalRequest.setAmount(tomanAmount * TomanToRialCurrency);

        callBackUrl = StringUtil.isNulOrEmpty(callBackUrl) ? defaultCallBackUrl : callBackUrl;
        if (StringUtil.isNulOrEmpty(callBackUrl))
            throw new IllegalArgumentException("call back url is not set");

        zFirstCalRequest.setCallback_url(callBackUrl);
        zFirstCalRequest.setDescription(description);
        zFirstCalRequest.setMerchant_id(MERCHANT_ID);


        var response = webClient.post()
                .uri(PAYMENT_INITIAL_URL)
                .bodyValue(zFirstCalRequest)
                .exchange()
                .flatMap(clientResponse -> {
                    //Error handling
                    if (clientResponse.statusCode().isError()) { // or clientResponse.statusCode().value() >= 400
                        return clientResponse.bodyToMono(String.class);

                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block();


        ZFirstCalResponse zFirstCalResponse = null;
        try {
            zFirstCalResponse = mapper.readValue(response, ZFirstCalResponse.class);
        } catch (IOException e) {
            return PurchaseLinkResponseBean.ofException(e.getMessage());
        }

        return PurchaseLinkResponseBean.ofZarinPal(zFirstCalResponse);
    }

    @Override
    public TransactionDataBean getTransactionData(String token) {
        //it is for vandar
        return null;
    }

    @Override
    public VerifyResponseBean verifyTransaction(String token, Integer tomanAmount) throws Exception {


        ZVerifyRequest zVerifyRequest = new ZVerifyRequest();
        zVerifyRequest.setAmount(tomanAmount * TomanToRialCurrency);
        zVerifyRequest.setAuthority(token);
        zVerifyRequest.setMerchant_id(MERCHANT_ID);


        AtomicInteger statusCode = new AtomicInteger();
        var response = webClient.post()
                .uri(VERIFY_URL)
                .bodyValue(zVerifyRequest)
                .exchange()
                .flatMap(clientResponse -> {
                    //Error handling
                    if (clientResponse.statusCode().isError()) { // or clientResponse.statusCode().value() >= 400
                        statusCode.set(clientResponse.statusCode().value());
                        return clientResponse.bodyToMono(String.class);
                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block();

        if (statusCode.get() >= 400) {
            return VerifyResponseBean.ofException(response);
        }

        ZVerifyResponse zVerifyResponse = null;
        try {
            zVerifyResponse = getObjectMapper().readValue(response, ZVerifyResponse.class);
        } catch (IOException e) {
            return VerifyResponseBean.ofException(e.getMessage());
        }


        return VerifyResponseBean.ofZarinPal(zVerifyResponse);
    }

    @Override
    public String getLinkOfPurchase(String token) {
        return ZARINPAL_START_URL + token;
    }

    @Override
    public VSettlementResponse transferMoney(Integer amount, String iban, String trackId, Integer paymentNumber, String notifyUrl, String description) throws Exception {
        throw new UnsupportedOperationException("");
    }

    @Override
    public FinancialProviderType getFinancialProviderType() {
        return FinancialProviderType.ZARINPAL;
    }

    @Override
    public String refreshToken() throws Exception {
        throw new UnsupportedOperationException();
    }


}
