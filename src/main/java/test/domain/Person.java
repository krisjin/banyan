package test.domain;

import java.util.List;

/**
 * User krisjin
 * Date 2017/7/12
 */
public class Person {

    private Long id;
    private List<Address> addressList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
