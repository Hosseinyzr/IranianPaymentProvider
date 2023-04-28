package model.vandar;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class VTransactionDataResponse {


    /*  "status": 1,
    "amount": "10000",
    "transId": 155058785697,
    "refnumber": "GmshtyjwKSuZXT81+6o9nKIkOcW*****PY05opjBoF",
    "trackingCode": "23***6",
    "factorNumber": null,
    "mobile": null,
    "description": "description",
    "cardNumber": "603799******6299",
    "CID": "ECC1F6931DDC1B8A0892293774836F3FFAC4A3C9D34997405F340FCC1BDDED82",
    "createdAt": "2019-02-19 18:19:55",
    "paymentDate": "2019-02-19 18:21:50",
    "code" : 1
    "message": "Confirm requierd"
*/

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



}
