package org.banyan.concurrent.model.xml;

import net.sf.json.xml.XMLSerializer;

/**
 * @author shijingui on 2021/10/10
 */
public class XMLSerializerTest {

    static String c = " <?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<msg>\n" +
            "<orderId>243242</orderId>\n" +
            "</msg>\n" +
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
