package com.thomas.netty.codec.marshalling;

import com.thomas.netty.codec.pojo.SubscribeReq;
import com.thomas.netty.codec.protobuf.SubscribeReqProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/30 15:34
 * @描述 TODO
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {
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
    public SubReqClientHandler() {
    }

    // ===========================================================
    // Methods
    // ===========================================================
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i <10 ; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReq subReq(int i) {
        SubscribeReq req = new SubscribeReq();
        req.setmAddress("NanJing YuHuaTai");
        req.setmPhoneNumber("138xxxxxxxxx");
        req.setmProductName("Netty Book For Marshalling");
        req.setmSubReqID(i);
        req.setmUserName("Thomasliu");
        return req;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server response : ["+msg+"]");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
