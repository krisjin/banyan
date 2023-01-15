package org.banyan.concurrent.invariant;

public final class Product {

    private final String code;//私有属性不会被其它对象获取
    private final String name;//final 保证不会被两次赋值
    private final String price;

    public Product(String code, String name, String price) {//在创建对象时，必须指定数据，因为创建之后，无法进行修改。
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
