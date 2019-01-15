package com.thomas.netty.protocol.http.xml.client;

import com.thomas.netty.protocol.http.xml.Order;
import com.thomas.netty.protocol.http.xml.codec.HttpXmlResponseDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

import java.net.InetSocketAddress;

/**
 * @创建人 thomas_liu
 * @创建时间 2019/1/8 11:09
 * @描述 TODO
 */
public class HttpXmlClient {
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
    public void connect(int port) throws Exception{
        //配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("http-decoder", new HttpResponseDecoder());
                            socketChannel.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            //XML解码器
//                            socketChannel.pipeline().addLast("xml-decoder", new HttpXmlResponseDecoder(Order.class, true));
//                            socketChannel.pipeline().addLast("http-encoder", new HttpRequestEncoder());
//                            socketChannel.pipeline().addLast("xml-encoder", new HttpXmlRequestEncoder());
//                            socketChannel.pipeline().addLast("xmlClientHandler", new HttpXmlClienthandler());
                        }
                    });

            //发起异步连接操作
            ChannelFuture f = b.connect(new InetSocketAddress(port)).sync();

            //等待客户端链路关闭
            f.channel().closeFuture().sync();

        }finally {
            //优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if(args != null && args.length >0){
            try{
                port = Integer.parseInt(args[0]);
            }catch(NumberFormatException e){
                //采用默认值
            }
        }

        new HttpXmlClient().connect(port);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
