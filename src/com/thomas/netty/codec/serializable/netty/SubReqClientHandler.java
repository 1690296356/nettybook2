package com.thomas.netty.codec.serializable.netty;

import com.thomas.netty.codec.pojo.SubscribeReq;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/3 11:23
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

    @SuppressWarnings("WeakerAccess")
    public SubReqClientHandler() {
    }


    // ===========================================================
    // Getter &amp; Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================


    @SuppressWarnings("RedundantThrows")
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReq subReq(int i){
        SubscribeReq req = new SubscribeReq();
        req.setmAddress("深圳市福田区莲花山公园");
        req.setmPhoneNumber("138XXXXXXXX");
        req.setmProductName("Netty 权威指南");
        req.setmSubReqID(i);
        req.setmUserName("Thomas");
        return req;
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server respose : ["+ msg +"]");
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
