package model.vandar;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VSettlementResponse {

    /*
    {
   "status":1,
   "data":{
      "settlement":[

      ]
   }
}
*/

    private Integer status;
    private VSettlementData data;



    public static VSettlementResponse makeFakeData() throws Exception {
        VSettlementResponse vSettlementResponse = new VSettlementResponse();
        vSettlementResponse.setStatus(1);
        vSettlementResponse.setData(VSettlementData.makeFakeData());
        return vSettlementResponse;
    }
}
