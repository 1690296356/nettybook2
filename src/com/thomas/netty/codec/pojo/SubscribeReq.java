package com.thomas.netty.codec.pojo;

import java.io.Serializable;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/8/31 17:29
 * @描述 TODO
 */
public class SubscribeReq  implements Serializable {
    // ===========================================================
    // Constants
    // ===========================================================
    private static final long serialVersionUID = 1L;

    // ===========================================================
    // Fields
    // ===========================================================
    private int mSubReqID;

    private String mUserName;

    private String mProductName;

    private String mPhoneNumber;

    private String mAddress;


    // ===========================================================
    // Constructors
    // ===========================================================


    // ===========================================================
    // Getter &amp; Setter
    // ===========================================================

    public int getmSubReqID() {
        return mSubReqID;
    }

    public void setmSubReqID(int mSubReqID) {
        this.mSubReqID = mSubReqID;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmProductName() {
        return mProductName;
    }

    public void setmProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================

    @Override
    public String toString() {
        return "SubscribeReq [subReqID="+ mSubReqID +", userName ="+mUserName+
                ", productName="+mProductName+", phoneNumber="+mPhoneNumber+", address="+mAddress+"}";
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
