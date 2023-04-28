package model.vandar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VSettlementWebhookData {

    /*
    *
    * {
    "id": "865e9d07-2c96-41ef-8f61-30de27fb9954"
    "iban_id": "72a8aab1-04c9-417e-9971-2f7bfe121b53"
    "transaction_id": "164258101221"
    "amount": "2500000"
    "payment_number": "123456"
    "status": "DONE"
    "settlement_date": "2022-01-19"
    "settlement_date_jalali": "1400/10/29"
    "revised_transaction_id": ""
    "ref_code": "140010290622047785"
}*/

    private String id;
    private String iban_id;
    private String transaction_id;
    private String amount;
    private String payment_number;
    private String status;
    private String settlement_date;
    private String settlement_date_jalali;
    private String revised_transaction_id;
    private String ref_code;

}
