package org.banyan.concurrent;

import com.alibaba.fastjson.JSONObject;
import com.jd.cost.sharing.sdk.dto.NewAccruedDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: krisjin
 * Date: 2021/10/20
 */
public class AAA {


    public static void main(String[] args) {
        List<NewAccruedDTO> data = new ArrayList<>();

        NewAccruedDTO accruedDTO = new NewAccruedDTO();

        //基础属性
        accruedDTO.setCurrency("CNY");
        accruedDTO.setThirdSubject("其他推广-引流拉新补贴");
        accruedDTO.setBusinessSummary("POP商品补贴");
        accruedDTO.setProfitDeptName("京东集团-京东零售-市场营销部-极速版业务部");
        accruedDTO.setAssumeDeptName("京东集团-京东零售-市场营销部-极速版业务部");
        accruedDTO.setTaxType(3);
        accruedDTO.setIsBusiness(2);
        accruedDTO.setTaxRate(0.06);
        accruedDTO.setProjectName("极速版POP商品补贴");
        //
        accruedDTO.setOrganizationName("江苏京东旭科信息技术有限公司");
        accruedDTO.setDevice("APP");

        //动态属性 127884
        accruedDTO.setContractNo("LBT20210000447");
        accruedDTO.setTrustee("renbaofeng3");
        accruedDTO.setSellerName("邢台金糖豆电子商务有限责任公司");
        accruedDTO.setProjectNumber("PB202107072464");
        accruedDTO.setMoney(3.0d);
        //2017年02月
        accruedDTO.setEstimatedTime("2017年09月");

        data.add(accruedDTO);

        System.out.println(JSONObject.toJSONString(data));

    }
}
