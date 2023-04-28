package model.zarinpal;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ZFirstCalRequest {

    private String merchant_id;
    private int amount;
    private String description;
    private String callback_url;
    // private metadata metadata;
    private List<String> metadata = new ArrayList<>();


}
