package model.zarinpal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ZarinPalConfig {

    private String merchantId;
    private String defaultCallBackUrl;

}
