package com.thomas.netty.codec.marshalling;

import com.thomas.netty.codec.pojo.SubscribeReq;
import com.thomas.netty.codec.pojo.SubscribeResp;
import com.thomas.netty.codec.protobuf.SubscribeReqProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/30 14:24
 * @描述 TODO
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {
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

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq) msg;
        if("Thomasliu".equalsIgnoreCase(req.getmUserName())){
            System.out.println("Service accept client subscrib req : ["+req.toString()+"]");
            ctx.writeAndFlush(resp(req.getmSubReqID()));
        }
    }

    @SuppressWarnings("Duplicates")
    private SubscribeResp resp(int subReqID){
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqID(subReqID);
        resp.setRespCode(0);
        resp.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
        return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();//发生异常，关闭链路
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
