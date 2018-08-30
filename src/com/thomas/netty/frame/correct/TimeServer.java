package com.thomas.netty.frame.correct;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/8/30 18:51
 * @描述 TODO
 */
public class TimeServer {
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
    public void bind(int pPort) throws Exception{
        //配置服务端的NIO线程组
        EventLoopGroup mBossGroup = new NioEventLoopGroup();
        EventLoopGroup mWorkerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap mServerBootstrap = new ServerBootstrap();
            mServerBootstrap.group(mBossGroup,mWorkerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());

            //绑定端口，同步等待成功
            ChannelFuture f = mServerBootstrap.bind(pPort);

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
        if(args != null&&args.length>0){
            try {
            port = Integer.valueOf(args[0]);
            }catch (Exception e){
                //采用默认值
            }
        }

        new TimeServer().bind(port);
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
            ch.pipeline().addLast(new StringDecoder());
            ch.pipeline().addLast(new TimeServerHandler());
        }
    }
}
