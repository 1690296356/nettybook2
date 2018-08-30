package com.thomas.netty.frame.fault;

import io.netty.channel.ChannelFuture;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/8/30 15:59
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
    @SuppressWarnings({"WeakerAccess", "Duplicates"})
    public void bind(int port) throws InterruptedException {
        //配置服务端的NIO线程组
        EventLoopGroup mBossGroup = new NioEventLoopGroup();
        EventLoopGroup mWorkerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap mServerBootstrap = new ServerBootstrap();
            mServerBootstrap.group(mBossGroup, mWorkerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());

            //绑定端口，同步等待成功
            ChannelFuture f = mServerBootstrap.bind(port).sync();
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        }finally {
            mBossGroup.shutdownGracefully();
            mWorkerGroup.shutdownGracefully();
        }
    }


    /**
     * @param args 参数
     * @throws Exception 异常信息
     */
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if(args != null && args.length >0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
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
        protected void initChannel(SocketChannel ch)  {
            ch.pipeline().addLast(new TimeServerHandler());
        }
    }

}
