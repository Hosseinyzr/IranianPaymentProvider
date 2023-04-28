package model.zarinpal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZDataVerify {

    private int code;
    private String message;
    private String card_hash;
    private String card_pan;
    private String fee_type;
    private long ref_id;
    private int fee;

}
