package model.vandar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VSettlementRequest {

    /*
amount	integer	required
iban	string	required
track_id	string	required
payment_number	integer	optional
notify_url	string	optional
description	string	optional
*/

    private Integer amount;
    private String iban;
    private String track_id;
    private Integer payment_number;
    private String notify_url;
    private String description;

}
