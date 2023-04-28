package model.vandar;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VSettlementData {

    /*
    {
   "status":1,
   "data":{
      "settlement":[
      ]
   }
}
*/

    private List<VSettlement> settlement;

    public static VSettlementData makeFakeData() throws Exception {
        VSettlementData vSettlementData = new VSettlementData();
        vSettlementData.setSettlement(List.of(VSettlement.makeFakeData()));
        return vSettlementData;
    }
}
