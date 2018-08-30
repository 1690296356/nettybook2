package com.thomas.netty.frame.correct;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/8/30 19:13
 * @描述 TODO
 */
public class TimeClient {
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
    public void connect(int port, String host) throws Exception{
        //配置客户端的NIO线程组
        EventLoopGroup mClientGroup = new NioEventLoopGroup();

        try {
            Bootstrap mClientBootstrap = new Bootstrap();
            mClientBootstrap.group(mClientGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new TimeClientHandler());
                        }
                    });

            //发起异步连接操作
            ChannelFuture f = mClientBootstrap.connect(host,port).sync();

            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        }finally {
            //优雅退出，释放NIO线程组
            mClientGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if(args != null&&args.length >0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }
        new TimeClient().connect(port, "127.0.0.1");
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
