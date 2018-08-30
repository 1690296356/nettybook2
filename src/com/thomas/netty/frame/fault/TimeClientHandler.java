package com.thomas.netty.frame.fault;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/8/30 16:59
 * @描述 TODO
 */
@SuppressWarnings("unused")
public class TimeClientHandler  extends ChannelHandlerAdapter {
    // ===========================================================
    // Constants
    // ===========================================================
    private Logger LOG = LoggerFactory.getLogger(TimeClientHandler.class);

    private int mCounter;

    private byte[] mReq;

    // ===========================================================
    // Fields
    // ===========================================================

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
        super.exceptionCaught(ctx, cause);
        LOG.info("Unecepted exception from downstream : [{}]",cause.getMessage());
        ctx.close();
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf message;
        for (int i = 0; i <100 ; i++) {
            message = Unpooled.buffer(mReq.length);
            message.writeBytes(mReq);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, StandardCharsets.UTF_8);
        System.out.println("Now is : "+body+" ; the counter is : "+ ++mCounter);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
