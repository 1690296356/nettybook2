package com.thomas.netty.protocol.http.xml.server;

import com.thomas.netty.protocol.http.xml.Address;
import com.thomas.netty.protocol.http.xml.Order;
import com.thomas.netty.protocol.http.xml.codec.HttpXmlRequest;
import com.thomas.netty.protocol.http.xml.codec.HttpXmlResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.ArrayList;
import java.util.List;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;

/**
 * @创建人 thomas_liu
 * @创建时间 2019/1/9 10:29
 * @描述 TODO
 */
public class HttpXmlServerHandler extends SimpleChannelInboundHandler<HttpXmlRequest> {
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
    protected void messageReceived(final ChannelHandlerContext ctx, HttpXmlRequest xmlRequest) throws Exception {
        HttpRequest request = (HttpRequest) xmlRequest.getRequest();
        Order order = (Order) xmlRequest.getBody();
        System.out.println("Http server receive request :"+order);
        dobusiness(order);
//        ChannelFuture future = ctx.writeAndFlush(new HttpXmlResponse(null, order));
//        if(!isKeepAlive( request)){
//            future.addListener(new GenericFutureListener<Future<? super Void>>(){
//                @Override
//                public void operationComplete(Future<? super Void> future) throws Exception {
//                    ctx.close();
//                }
//            });
//        }


    }

    private void dobusiness(Order order){
        order.getCustomer().setFirstName("Bryant");
        order.getCustomer().setLastName("Kobe");
        List<String> midNames = new ArrayList<String>();
        midNames.add("paul gasoul");
        order.getCustomer().setMiddleNames(midNames);
        Address address = order.getBillTo();
        address.setCity("Los Angles");
        address.setCountry("USA");
        address.setState("948 Parrill Court");
        address.setPostCode("123456");
        order.setBillTo(address);
        order.setShipTo(address);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        if(ctx.channel().isActive()){
            sendError(ctx, INTERNAL_SERVER_ERROR);
        }
    }


    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status){
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer("失败: "+status.toString()+"\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8" );
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
