package com.thomas.netty.frame.correct;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/8/30 19:05
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
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String)msg;
        System.out.println("The time server receive order : "+body+" ; the counter is :"+ ++mCounter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";

        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
