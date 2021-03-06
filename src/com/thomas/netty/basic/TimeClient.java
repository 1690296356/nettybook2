package com.thomas.netty.basic;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
	
	@SuppressWarnings("Duplicates")
	private void connect(int pPort, String pHost) throws Exception{

		//配置客戶端NIO线程组
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel arg0)  {
							arg0.pipeline().addLast(new TimeClientHandler());
						}
					});

			//发起异步连接操作
			ChannelFuture f = b.connect(pHost, pPort).sync();

			//等待客户端链路关闭
			f.channel().closeFuture().sync();

		} finally {
			//优雅退出，释放NIO线程组
			group.shutdownGracefully();
		}
		
		
	}
	
	public static void main(String[] args) throws Exception {
		int port = 8080;
		if(args != null && args.length >0){
			try {
				port = Integer.valueOf(args[0]);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		new TimeClient().connect(port, "127.0.0.1");
	}

}
