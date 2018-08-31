package com.thomas.netty.codec.serializable;


import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/8/31 15:25
 * @描述 TODO
 */
@SuppressWarnings("unused")
public class UserInfo implements Serializable {
    // ===========================================================
    // Constants
    // ===========================================================
    public static final long serialVersionUID = 1L;


    // ===========================================================
    // Fields
    // ===========================================================
    private String mUserName;

    private int mUserID;
    // ===========================================================
    // Constructors
    // ===========================================================
    public UserInfo buildUserName(String pUserName){
        this.mUserName = pUserName;
        return this;
    }


    public UserInfo buildUserID(int pUserID){
        this.mUserID = pUserID;
        return this;
    }


    // ===========================================================
    // Getter &amp; Setter
    // ===========================================================

    @SuppressWarnings("unused")
    public String getmUserName() {
        return mUserName;
    }

    @SuppressWarnings("unused")
    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    @SuppressWarnings("unused")
    public int getmUserID() {
        return mUserID;
    }

    @SuppressWarnings("unused")
    public void setmUserID(int mUserID) {
        this.mUserID = mUserID;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================
    @SuppressWarnings({"UnusedAssignment", "unused"})
    public byte[] codeC(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.mUserName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.mUserID);
        buffer.flip();
        value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }


    @SuppressWarnings({"UnusedAssignment", "unused"})
    public byte[] codeC(ByteBuffer buffer){
        buffer.clear();
        byte[] value = this.mUserName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.mUserID);
        buffer.flip();
        value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
