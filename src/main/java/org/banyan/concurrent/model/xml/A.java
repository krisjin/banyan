package org.banyan.concurrent.model.xml;

import net.sf.json.xml.XMLSerializer;

/**
 * User: krisjin
 * Date: 2021/9/26
 */
public class A {
    static String s = "<BillMessageVO><orderId>222441576922</orderId><orderStatus>19</orderStatus><venderId>10415201</venderId><accountId>94459510000</accountId><rfBillType>27819001</rfBillType><statementId>0</statementId><beginDate>0</beginDate><endDate>0</endDate><uuid>6db02440c40abac5dc53aa79c83a2fac</uuid><messageSendTime>2021-09-24 19:55:29</messageSendTime><messagekey>billing_system_callback</messagekey><resultStatus>1</resultStatus><description>订单:[222441576922],计费明细:[6db02440c40abac5dc53aa79c83a2fac],结算状态为:[1]</description><serviceFeeType>30</serviceFeeType><orderType>22</orderType><resultTime>0</resultTime><settleType>0</settleType><bal>7.56</bal><direction>1</direction></BillMessageVO>";

    static String c = " <?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<BillMessageVO>\n" +
            "<orderId>223898717699</orderId>\n" +
            "<orderStatus>19</orderStatus>\n" +
            "<venderId>60770</venderId>\n" +
            "<accountId>3836120000</accountId>\n" +
            "<rfBillType>21800001</rfBillType>\n" +
            "<statementId>0</statementId>\n" +
            "<beginDate>0</beginDate>\n" +
            "<endDate>0</endDate>\n" +
            "<uuid>10a15a84223137b5c9ffc4d2755b625b</uuid>\n" +
            "<messageSendTime>2021-09-28 11:20:40</messageSendTime>\n" +
            "<messagekey>billing_system_callback</messagekey>\n" +
            "<resultStatus>1</resultStatus>\n" +
            "<description>订单:[223898717699],计费明细:[10a15a84223137b5c9ffc4d2755b625b],结算状态为:[1]</description>\n" +
            "<serviceFeeType>18</serviceFeeType>\n" +
            "<orderType>2</orderType>\n" +
            "<resultTime>0</resultTime>\n" +
            "<settleType>0</settleType>\n" +
            "<bal>0.14</bal>\n" +
            "<direction>-1</direction>\n" +
            "</BillMessageVO>\n" +
            "";


    public static void main(String[] args) {
        String newStr = new String();
        String parseStr = "utf-8a";
        int i = 0;
        if ((i = c.indexOf(parseStr)) != -1) {
            newStr = c.substring(i + 8).trim();
        }
        System.err.println(toJSONString(newStr));
    }

    public static String toJSONString(String xml) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        String resutStr = xmlSerializer.read(xml).toString(); //0表示去除换行空格等，1以上表示json格式化后的数据
        return resutStr;
    }

}
