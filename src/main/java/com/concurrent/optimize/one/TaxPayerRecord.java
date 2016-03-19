package com.concurrent.optimize.one;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 纳税人记录
 * User: shijingui
 * Date: 2016/3/19
 */
public class TaxPayerRecord {
    private String firstName;
    private String lastName;
    private String ssn;
    private String address;
    private String city;
    private String state;
    private AtomicLong taxPaid;


    public TaxPayerRecord(String firstName, String lastName, String ssn, String address, String city, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.address = address;
        this.city = city;
        this.state = state;
        this.taxPaid = new AtomicLong(0);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public AtomicLong getTaxPaid() {
        return taxPaid;
    }

    public void setTaxPaid(AtomicLong taxPaid) {
        this.taxPaid = taxPaid;
    }
}
