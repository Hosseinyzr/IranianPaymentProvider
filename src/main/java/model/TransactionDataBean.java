package model;

import lombok.Getter;
import lombok.Setter;
import model.vandar.VTransactionDataResponse;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class TransactionDataBean {
    private int resultCode;


    private int status;
    private String amount;
    private BigDecimal transId;
    private String refnumber;
    private String trackingCode;
    private String factorNumber;
    private String mobile;
    private String description;
    private String cardNumber;
    private String CID;
    private String createdAt;
    private String paymentDate;
    private Integer code;
    private String message;
    private List<String> errors;

    private VTransactionDataResponse vTransactionDataResponse;

    public static TransactionDataBean ofVandar(VTransactionDataResponse vTransactionDataResponse) {

        TransactionDataBean transactionDataBean = new TransactionDataBean();
        transactionDataBean.setStatus(vTransactionDataResponse.getStatus());
        transactionDataBean.setAmount(vTransactionDataResponse.getAmount());
        transactionDataBean.setTransId(vTransactionDataResponse.getTransId());
        transactionDataBean.setRefnumber(vTransactionDataResponse.getRefnumber());
        transactionDataBean.setTrackingCode(vTransactionDataResponse.getTrackingCode());
        transactionDataBean.setFactorNumber(vTransactionDataResponse.getFactorNumber());
        transactionDataBean.setMobile(vTransactionDataResponse.getMobile());
        transactionDataBean.setDescription(vTransactionDataResponse.getDescription());
        transactionDataBean.setCardNumber(vTransactionDataResponse.getCardNumber());
        transactionDataBean.setCID(vTransactionDataResponse.getCID());
        transactionDataBean.setCreatedAt(vTransactionDataResponse.getCreatedAt());
        transactionDataBean.setPaymentDate(vTransactionDataResponse.getPaymentDate());
        transactionDataBean.setCode(vTransactionDataResponse.getCode());
        transactionDataBean.setMessage(vTransactionDataResponse.getMessage());
        transactionDataBean.setErrors(vTransactionDataResponse.getErrors());

        transactionDataBean.setResultCode(vTransactionDataResponse.getStatus() == 1 ? 0 : 1);

        transactionDataBean.setVTransactionDataResponse(vTransactionDataResponse);

        return transactionDataBean;

    }
    public static TransactionDataBean ofException(String message) {

        TransactionDataBean transactionDataBean = new TransactionDataBean();
        transactionDataBean.setMessage(message);
        transactionDataBean.setResultCode(1);

        return transactionDataBean;

    }

}
