package model;

import lombok.Getter;
import lombok.Setter;
import model.vandar.VVerifyResponse;
import model.zarinpal.ZVerifyResponse;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class VerifyResponseBean {

    private int resultCode;

    private Integer status;
    private String amount;
    private Integer realAmount;
    private String wage;
    private BigDecimal transId;
    private String factorNumber;
    private String mobile;
    private String description;
    private String cardNumber;
    private String paymentDate;
    private String cid;
    private String message;
    private List<String> errors;
    private String cardHash;
    private String fee_type;
    private long ref_id;
    private int fee;

    private VVerifyResponse vVerifyResponse;
    private ZVerifyResponse zVerifyResponse;

    public static VerifyResponseBean ofVandar(VVerifyResponse vVerifyResponse) {
        VerifyResponseBean verifyResponseBean = new VerifyResponseBean();
        verifyResponseBean.setStatus(vVerifyResponse.getStatus());
        verifyResponseBean.setAmount(vVerifyResponse.getAmount());
        verifyResponseBean.setRealAmount(vVerifyResponse.getRealAmount());
        verifyResponseBean.setWage(vVerifyResponse.getWage());
        verifyResponseBean.setTransId(vVerifyResponse.getTransId());
        verifyResponseBean.setFactorNumber(vVerifyResponse.getFactorNumber());
        verifyResponseBean.setMobile(vVerifyResponse.getMobile());
        verifyResponseBean.setDescription(vVerifyResponse.getDescription());
        verifyResponseBean.setCardNumber(vVerifyResponse.getCardNumber());
        verifyResponseBean.setPaymentDate(vVerifyResponse.getPaymentDate());
        verifyResponseBean.setCid(vVerifyResponse.getCid());
        verifyResponseBean.setMessage(vVerifyResponse.getMessage());
        verifyResponseBean.setErrors(vVerifyResponse.getErrors());

        verifyResponseBean.setResultCode(vVerifyResponse.getStatus() == 1 ? 0 : 1);

        verifyResponseBean.setVVerifyResponse(vVerifyResponse);


        return verifyResponseBean;
    }

    public static VerifyResponseBean ofZarinPal(ZVerifyResponse zVerifyResponse) {
        var data = zVerifyResponse.getData();
        var errors = zVerifyResponse.getErrors();

        VerifyResponseBean verifyResponseBean = new VerifyResponseBean();
        verifyResponseBean.setStatus(data.getCode());
        verifyResponseBean.setMessage(data.getMessage());
        verifyResponseBean.setCardHash(data.getCard_hash());
        verifyResponseBean.setCardNumber(data.getCard_pan());
        verifyResponseBean.setFee(data.getFee());
        verifyResponseBean.setFee_type(data.getFee_type());
        verifyResponseBean.setRef_id(data.getRef_id());
        verifyResponseBean.setErrors(errors);


        verifyResponseBean.setResultCode((data.getCode() == 100 || data.getCode() == 101) ? 0 : 1);

        verifyResponseBean.setZVerifyResponse(zVerifyResponse);

        return verifyResponseBean;
    }

    public static VerifyResponseBean ofException(String message) {
        VerifyResponseBean verifyResponseBean = new VerifyResponseBean();
        verifyResponseBean.setMessage(message);
        verifyResponseBean.setResultCode(1);
        return verifyResponseBean;
    }

}
