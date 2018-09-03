package com.thomas.netty.codec.serializable.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/3 11:12
 * @描述 TODO
 */
public class SubReqClient {
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
    public void connect(int port, String host) throws Exception{
        //配置客户端NIO线程组
        EventLoopGroup mClientGroup = new NioEventLoopGroup();
        try {
            Bootstrap mClientBootstrap = new Bootstrap();
            mClientBootstrap.group(mClientGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @SuppressWarnings("RedundantThrows")
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ObjectDecoder(1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                            ch.pipeline().addLast(new ObjectEncoder());
                            ch.pipeline().addLast(new SubReqClientHandler());
                        }
                    });
            //发起异步连接操作
            ChannelFuture f = mClientBootstrap.connect(host, port).sync();

            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        }finally {
            //优雅退出，释放NIO线程组
            mClientGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if(args != null && args.length >0){
            try {
            port = Integer.parseInt(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }
        new SubReqClient().connect(port, "127.0.0.1");
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
