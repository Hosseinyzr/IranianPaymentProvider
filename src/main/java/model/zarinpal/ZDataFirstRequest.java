package model.zarinpal;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.List;

@Getter
@Setter
public class ZDataFirstRequest {

    private String merchant_id;
    private int amount;
    private String description;
    private String callback_url;
    private List<String> metadata;
    private String mobile;
    private String email;

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
