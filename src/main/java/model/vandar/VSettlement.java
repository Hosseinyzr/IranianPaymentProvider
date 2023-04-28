package model.vandar;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VSettlement {

    /*

         {
            "id":"406f20d0-397a-11ec-b752-6b667e3fe6ba",
            "iban_id":"083510a0-f998-11eb-8c11-27d425426e06",
            "transaction_id":163559577615,
            "amount":50000,
            "amount_toman":5000,
            "wage_toman":0,
            "payment_number":null,
            "status":"PENDING",
            "wallet":"221500",
            "description":"تسویه حساب وندار",
            "settlement_date":"2021-10-30",
            "settlement_time":"15:39:36",
            "settlement_date_jalali":"1400\/08\/08",
            "settlement_done_time_prediction":"1400\/08\/08 16:00:00",
            "is_instant":false,
            "prediction":{
               "date":"1400\/8\/8",
               "time":"16:00:00",
               "extra":"امروز"
            },
            "receipt_url": "https:\/\/vand.ar\/Mcz6C"
         }

*/

    private String id;
    private String iban_id;
    private BigDecimal transaction_id;
    private Integer amount;
    private Integer amount_toman;
    private Integer wage_toman;
    private Integer payment_number;
    private String status;
    private String wallet;
    private String description;
    private String settlement_date;
    private String settlement_time;
    private String settlement_date_jalali;
    private String settlement_done_time_prediction;
    private Boolean is_instant;
    private VSettlementPrediction prediction;
    private String receipt_url;


    public static VSettlement makeFakeData() throws Exception {
        String json = " {\n" +
                "            \"id\":\"406f20d0-397a-11ec-b752-6b667e3fe6ba\",\n" +
                "            \"iban_id\":\"083510a0-f998-11eb-8c11-27d425426e06\",\n" +
                "            \"transaction_id\":163559577615,\n" +
                "            \"amount\":50000,\n" +
                "            \"amount_toman\":5000,\n" +
                "            \"wage_toman\":0,\n" +
                "            \"payment_number\":null,\n" +
                "            \"status\":\"PENDING\",\n" +
                "            \"wallet\":\"221500\",\n" +
                "            \"description\":\"تسویه حساب وندار\",\n" +
                "            \"settlement_date\":\"2021-10-30\",\n" +
                "            \"settlement_time\":\"15:39:36\",\n" +
                "            \"settlement_date_jalali\":\"1400\\/08\\/08\",\n" +
                "            \"settlement_done_time_prediction\":\"1400\\/08\\/08 16:00:00\",\n" +
                "            \"is_instant\":false,\n" +
                "            \"prediction\":{\n" +
                "               \"date\":\"1400\\/8\\/8\",\n" +
                "               \"time\":\"16:00:00\",\n" +
                "               \"extra\":\"امروز\"\n" +
                "            },\n" +
                "            \"receipt_url\": \"https:\\/\\/vand.ar\\/Mcz6C\"\n" +
                "         }";

        ObjectMapper mapper = new ObjectMapper();
        var fakeObject = mapper.readValue(json, VSettlement.class);
        return fakeObject;
    }



}
