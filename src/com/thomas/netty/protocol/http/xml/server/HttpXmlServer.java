package com.thomas.netty.protocol.http.xml.server;

import com.thomas.netty.protocol.http.xml.Order;
import com.thomas.netty.protocol.http.xml.codec.HttpXmlRequestDecoder;
import com.thomas.netty.protocol.http.xml.codec.HttpXmlResponseEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @创建人 thomas_liu
 * @创建时间 2019/1/9 11:00
 * @描述 TODO
 */
public class HttpXmlServer {
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
//    public void run(final int port) throws Exception{
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try{
//            ServerBootstrap b = new ServerBootstrap();
////            b.group(bossGroup, workerGroup)
////                    .channel((Class<? extends ServerChannel>) NioSocketChannel.class)
////                    .childHandler(new ChannelInitializer<SocketChannel>() {
////                        @Override
////                        protected void initChannel(SocketChannel socketChannel) throws Exception {
////                            socketChannel.pipeline().addLast("http-decoder", new HttpRequestEncoder());
////                            socketChannel.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
//////                            socketChannel.pipeline().addLast("xml-decoder", new HttpXmlRequestDecoder(Order.class, true));
////                            socketChannel.pipeline().addLast("http-encoder", new HttpResponseEncoder());
////                            socketChannel.pipeline().addLast("xml-encoder", new HttpXmlResponseEncoder());
////                            socketChannel.pipeline().addLast("xmlServerHandler", new HttpXmlServerHandler());
////                        }
////                    });
////        }finally {
////
////        }
//
//
//    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
