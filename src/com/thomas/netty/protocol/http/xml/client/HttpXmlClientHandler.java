package com.thomas.netty.protocol.http.xml.client;

import com.thomas.netty.protocol.http.xml.OrderFactory;
import com.thomas.netty.protocol.http.xml.codec.HttpXmlRequest;
import com.thomas.netty.protocol.http.xml.codec.HttpXmlResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @创建人 thomas_liu
 * @创建时间 2019/1/8 19:34
 * @描述 TODO
 */
public class HttpXmlClientHandler extends SimpleChannelInboundHandler<HttpXmlResponse> {
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
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        HttpXmlRequest request = new HttpXmlRequest(null, OrderFactory.create(123));
        ctx.writeAndFlush(request);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, HttpXmlResponse msg) throws Exception {
        System.out.println("The client receive response of http header is:"+msg.getHttpResponse().headers().names());
        System.out.println("The client receive response of http body is:"+msg.getResult());
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
