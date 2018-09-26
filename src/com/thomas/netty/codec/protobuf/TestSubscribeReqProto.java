package com.thomas.netty.codec.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/26 14:37
 * @描述 TODO
 */
public class TestSubscribeReqProto {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================


    // ===========================================================
    // Getter &amp; Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================
    private static byte[] encode(SubscribeReqProto.SubscribeReq req){
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body)
            throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq
                .newBuilder();
        builder.setSubReqId(1);
        builder.setUserName("Thoams liu");
        builder.setProductName("Netty Book");
        List<String> address = new ArrayList<>();
        address.add("NanJing YuHuaTai");
        address.add("BeiJing LiuLiChang");
        address.add("ShenZhen HongShuLin");
        builder.addAllAddress(address);
        return builder.build();
    }

    /**
     * @param args
     * @throws InvalidProtocolBufferException
     * protoc生成代码命令
     * protoc --java_out=D:\  -ID:\test_protoc D:\test_protoc\SubscribeResp.proto
     */
    public static void main(String[] args)
            throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("Before encode : "+req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));

        System.out.println("After decode : " + req.toString());
        System.out.println(" Assert equal : --> "+req2.equals(req));
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
