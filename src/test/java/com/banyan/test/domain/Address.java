package com.banyan.test.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * User krisjin
 * Date 2017/7/12
 */
public class Address {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Detail> detailList) {
        this.detailList = detailList;
    }

    private List<Detail> detailList;

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d = new Date();

        d.setTime(1584606703684L);
       String a= simpleDateFormat.format(d);
        System.err.println(a);
    }
}
