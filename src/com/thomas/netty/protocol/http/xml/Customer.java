package com.thomas.netty.protocol.http.xml;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2019/1/7 11:18
 * @描述 TODO
 */
public class Customer {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    private long customerNumber;

    private String firstName;

    private String lastName;

    private List<String> middleNames;


    // ===========================================================
    // Constructors
    // ===========================================================


    @Override
    public String toString() {
        return super.toString();
    }


    // ===========================================================
    // Getter &amp; Setter
    // ===========================================================

    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
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

    public List<String> getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(List<String> middleNames) {
        this.middleNames = middleNames;
    }


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
