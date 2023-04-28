package model.vandar;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VandarConfig {

    private String accessToken;
    private String refreshToken;
    private String businessName;
    private String apiKey;
    private String defaultCallBackUrl;
}
