package com.thomas.netty.codec.serializable.netty;

import com.thomas.netty.codec.pojo.SubscribeReq;
import com.thomas.netty.codec.pojo.SubscribeResp;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/3 10:23
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

    @SuppressWarnings("RedundantThrows")
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq) msg;

        if("Thomas".equalsIgnoreCase(req.getmUserName())){
            System.out.println("Service accept client subscribe req : ["+ req.toString() +"]");
            ctx.writeAndFlush(resp(req.getmSubReqID()));
        }
    }

    private SubscribeResp resp(int subReqID){
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqID(subReqID);
        resp.setRespCode(0);
        resp.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
        return resp;
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       cause.printStackTrace();
       //发生异常，关闭链路
       ctx.close();

    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
