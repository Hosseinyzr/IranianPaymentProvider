package model.zarinpal;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ZVerifyResponse {

    private ZDataVerify data;

    private List<String> errors;



}
