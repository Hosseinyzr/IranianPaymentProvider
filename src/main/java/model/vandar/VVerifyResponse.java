package model.vandar;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class VVerifyResponse {


    /*"status": 1,
    "amount": "1000.00",
    "realAmount": 500,
    "wage": "500",
    "transId": 159178352177,
    "factorNumber": "12345",
    "mobile": "09123456789",
    "description": "description",
    "cardNumber": "603799******7999",
    "paymentDate": "2020-06-10 14:36:30",
    "cid": null,
    "message": "ok"
   */

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

}
