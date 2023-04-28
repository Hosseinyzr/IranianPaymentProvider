package model;

import lombok.Getter;
import lombok.Setter;
import model.vandar.VDataFirst;
import model.zarinpal.ZFirstCalResponse;

import java.util.List;

@Getter
@Setter
public class PurchaseLinkResponseBean {

    //0 means success and 1 is unsuccessful
    private int resultCode;


    private int statusCode;
    private String message;
    private String token;
    private String fee_type;
    private int fee;

    private VDataFirst vDataFirst;
    private ZFirstCalResponse zFirstCalResponse;

    private List<String> errors;

    public static PurchaseLinkResponseBean ofVandar(VDataFirst vDataFirst) {
        PurchaseLinkResponseBean responseBean = new PurchaseLinkResponseBean();


        var errors = vDataFirst.getErrors();
        var status = vDataFirst.getStatus();
        var token = vDataFirst.getToken();
        responseBean.setStatusCode(status);
        responseBean.setToken(token);
        responseBean.setErrors(errors);

        responseBean.setResultCode(vDataFirst.getStatus() == 1 ? 0 : 1);

        responseBean.setVDataFirst(vDataFirst);


        return responseBean;
    }

    public static PurchaseLinkResponseBean ofZarinPal(ZFirstCalResponse zFirstCalResponse) {
        PurchaseLinkResponseBean responseBean = new PurchaseLinkResponseBean();


        var data = zFirstCalResponse.getData();
        var errors = zFirstCalResponse.getErrors();

        if (data != null) {
            var code = data.getCode();
            var authority = data.getAuthority();
            var fee = data.getFee();
            var fee_type = data.getFee_type();
            var message = data.getMessage();
            responseBean.setStatusCode(code);
            responseBean.setMessage(message);
            responseBean.setToken(authority);
            responseBean.setFee_type(fee_type);
            responseBean.setFee(fee);
            responseBean.setResultCode((data.getCode() == 100 || data.getCode() == 101) ? 0 : 1);

        }
        responseBean.setErrors(errors);

        responseBean.setZFirstCalResponse(zFirstCalResponse);


        return responseBean;
    }

    public static PurchaseLinkResponseBean ofException(String message) {
        PurchaseLinkResponseBean responseBean = new PurchaseLinkResponseBean();
        responseBean.setMessage(message);
        responseBean.setResultCode(1);
        return responseBean;
    }

}
