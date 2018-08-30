package com.thomas.netty.frame.correct;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/8/30 19:27
 * @描述 TODO
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    // ===========================================================
    // Constants
    // ===========================================================
    private static final Logger LOG = LoggerFactory.getLogger(TimeClientHandler.class);

    // ===========================================================
    // Fields
    // ===========================================================
    private int mCounter;
    private byte[] mReq;
    // ===========================================================
    // Constructors
    // ===========================================================

    public TimeClientHandler() {
        mReq = ("QUERY TIME ORDER"+ System.getProperty("line.separator")).getBytes();
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

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOG.info("Unexcepted exception from downstream : [{}]",cause.getMessage());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i <100 ; i++) {
            message = Unpooled.buffer(mReq.length);
            message.writeBytes(mReq);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("Now is : "+ body +" ; the counter is : "+ ++mCounter);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
