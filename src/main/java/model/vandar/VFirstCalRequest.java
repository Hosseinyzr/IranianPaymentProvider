package model.vandar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VFirstCalRequest {


    /*api_key	string	required
amount	Integer	required
callback_url	String	required
mobile_number	String	optional
factorNumber	String	optional
description	String	optional
national_code	String	optional
valid_card_number
*/

    private String api_key;
    private Integer amount;
    private String callback_url;
    private String mobile_number;
    private String factorNumber;
    private String description;
    private String national_code;
    private String valid_card_number;


}
