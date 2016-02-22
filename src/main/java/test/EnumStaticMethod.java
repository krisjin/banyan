package test;

import java.util.HashMap;
import java.util.Map;

/**
 * User: shijingui
 * Date: 2016/2/22
 */
public enum EnumStaticMethod {
    PC(1, "PC"),
    MOBILE(2, "MOBILE");

    private int key;
    private String value;

    EnumStaticMethod(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Map<Integer, String> getEnumMap() {
        Map<Integer, String> enumMap = new HashMap<Integer, String>();
        EnumStaticMethod[] enumStaticMethods = EnumStaticMethod.values();
        for (EnumStaticMethod enumStaticMethod : enumStaticMethods) {
            enumMap.put(enumStaticMethod.getKey(), enumStaticMethod.getValue());
        }
        return enumMap;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }


}
