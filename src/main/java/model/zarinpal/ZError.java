package model.zarinpal;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ZError {
    private Integer code;
    private String message;
    private List<String> validations;

}
