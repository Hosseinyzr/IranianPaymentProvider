package model.zarinpal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ZVerifyRequest {

    private String merchant_id;
    private int amount;
    private String authority;

}
