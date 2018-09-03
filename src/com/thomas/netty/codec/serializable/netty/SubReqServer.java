package com.thomas.netty.codec.serializable.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/3 10:06
 * @描述 TODO
 */
public class SubReqServer {
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
    public void bind(int pPort) throws  Exception{
        //配置服务端的NIO线程组
        EventLoopGroup mBossGroup = new NioEventLoopGroup();
        EventLoopGroup mWorkerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap mServerBootstrap = new ServerBootstrap();
            mServerBootstrap.group(mBossGroup, mWorkerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @SuppressWarnings("RedundantThrows")
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ObjectDecoder(1024*1024, ClassResolvers
                                    .weakCachingConcurrentResolver(this.getClass().getClassLoader())));

                            ch.pipeline().addLast(new ObjectEncoder());
                            ch.pipeline().addLast(new SubReqServerHandler());
                        }
                    });
            //绑定端口，同步等待成功
            ChannelFuture f = mServerBootstrap.bind(pPort).sync();

            //等待服务端监听端口关闭
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

        new SubReqServer().bind(port);
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
