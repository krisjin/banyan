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

        //��������
        accruedDTO.setCurrency("CNY");
        accruedDTO.setThirdSubject("�����ƹ�-�������²���");
        accruedDTO.setBusinessSummary("POP��Ʒ����");
        accruedDTO.setProfitDeptName("��������-��������-�г�Ӫ����-���ٰ�ҵ��");
        accruedDTO.setAssumeDeptName("��������-��������-�г�Ӫ����-���ٰ�ҵ��");
        accruedDTO.setTaxType(3);
        accruedDTO.setIsBusiness(2);
        accruedDTO.setTaxRate(0.06);
        accruedDTO.setProjectName("���ٰ�POP��Ʒ����");
        //
        accruedDTO.setOrganizationName("���վ��������Ϣ�������޹�˾");
        accruedDTO.setDevice("APP");

        //��̬���� 127884
        accruedDTO.setContractNo("LBT20210000447");
        accruedDTO.setTrustee("renbaofeng3");
        accruedDTO.setSellerName("��̨���Ƕ����������������ι�˾");
        accruedDTO.setProjectNumber("PB202107072464");
        accruedDTO.setMoney(3.0d);
        //2017��02��
        accruedDTO.setEstimatedTime("2017��09��");

        data.add(accruedDTO);

        System.out.println(JSONObject.toJSONString(data));

    }
}
