package com.thomas.netty.frame.fault;

import com.thomas.netty.basic.TimeClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/8/30 16:41
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

    @SuppressWarnings({"WeakerAccess", "Duplicates"})
    public void connect(int port, String host) throws InterruptedException {
        //配置客户端的NIO线程组
        EventLoopGroup mClinetGroup = new NioEventLoopGroup();
        try{
            Bootstrap mClientBootStrap = new Bootstrap();
            mClientBootStrap.group(mClinetGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new TimeClientHandler());
                        }
                    });

            //发起异步连接操作
            ChannelFuture f = mClientBootStrap.connect(host, port).sync();

            // 等待客户端链路关闭
            f.channel().closeFuture().sync();
        }finally {
            //优雅退出，释放NIO线程组
            mClinetGroup.shutdownGracefully();
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
            }catch(NumberFormatException e) {
                //采用默认值
            }
        }
        new TimeClient().connect(port, "127.0.0.1");

    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
