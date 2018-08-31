package com.thomas.netty.codec.pojo;

import java.io.Serializable;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/8/31 17:38
 * @描述 TODO
 */
@SuppressWarnings("unused")
public class SubscribeResp implements Serializable {
    // ===========================================================
    // Constants
    // ===========================================================
    private static final long serialVersionUID = 1L;

    // ===========================================================
    // Fields
    // ===========================================================
    private int mSubReqID;

    private int mRespCode;

    private String mDesc;
    // ===========================================================
    // Constructors
    // ===========================================================


    // ===========================================================
    // Getter &amp; Setter
    // ===========================================================

    public int getSubReqID() {
        return mSubReqID;
    }

    public void setSubReqID(int pSubReqID) {
        this.mSubReqID = pSubReqID;
    }

    public int getRespCode() {
        return mRespCode;
    }

    public void setRespCode(int pRespCode) {
        this.mRespCode = pRespCode;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String pDesc) {
        this.mDesc = pDesc;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================

    @Override
    public String toString() {
        return "SubcribeResp [subReqID="+mSubReqID+" , respCode="+mRespCode+", desc="+mDesc+"]";
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
