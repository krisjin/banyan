package com.banyan.test.domain;

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
}
