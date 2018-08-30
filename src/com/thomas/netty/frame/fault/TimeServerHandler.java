package com.thomas.netty.frame.fault;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/8/30 16:16
 * @描述 TODO
 */
public class TimeServerHandler extends ChannelHandlerAdapter {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    private int mCounter;
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
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, StandardCharsets.UTF_8).substring(0, req.length - System.getProperty("line.separator").length());
        System.out.println("The time server receive order :" +body+ " ;the counter is : "+ ++mCounter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)? new java.util.Date(System.currentTimeMillis()).toString() :"BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
