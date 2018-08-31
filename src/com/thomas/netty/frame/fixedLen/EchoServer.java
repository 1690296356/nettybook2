package com.thomas.netty.frame.fixedLen;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/8/31 14:17
 * @描述 TODO
 */
public class EchoServer {
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
    @SuppressWarnings("WeakerAccess")
    public void bind(int port) throws  Exception{
        //配置服务端的NIO线程组
        EventLoopGroup mBossGroup = new NioEventLoopGroup();
        EventLoopGroup mWorkerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap mServerBootstrap = new ServerBootstrap();
            mServerBootstrap.group(mBossGroup, mWorkerGroup)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @SuppressWarnings("RedundantThrows")
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new FixedLengthFrameDecoder(20));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new EchoServerHandler());
                }
            });
            //绑定端口，同步等待成功
            ChannelFuture f = mServerBootstrap.bind(port).sync();
            //等待服务器监听端口关闭
            f.channel().closeFuture().sync();
        }finally {
            //优雅退出，释放线程池资源
            mBossGroup.shutdownGracefully();
            mWorkerGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws Exception {
        int port = 8080;
        if(args != null && args.length >0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }

        new EchoServer().bind(port);
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
