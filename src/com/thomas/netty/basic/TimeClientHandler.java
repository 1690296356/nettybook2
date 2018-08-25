package com.thomas.netty.basic;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class TimeClientHandler  extends ChannelHandlerAdapter {
	
	private static final Logger LOG = LoggerFactory.getLogger( TimeClientHandler.class);
	
	
	private final ByteBuf  mFirstMessage;
	

	@SuppressWarnings("WeakerAccess")
	public TimeClientHandler() {
		byte[] req = "QUERY TIME ORDER".getBytes();
		mFirstMessage = Unpooled.buffer(req.length);
		mFirstMessage.writeBytes(req);
	}
	
	

	@Override
	public void channelActive(ChannelHandlerContext pCtx) {
		pCtx.writeAndFlush(mFirstMessage);
	}



	@Override
	public void channelRead(ChannelHandlerContext pCtx, Object pMsg) {
		ByteBuf buf = (ByteBuf) pMsg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, StandardCharsets.UTF_8);
		System.out.println("Now is:" + body);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext pCtx) throws Exception {
		super.channelReadComplete(pCtx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext pCtx, Throwable pCause) {
		// 释放资源
		LOG.info("Unexpected exception from downstream [{}]",pCause.getMessage());
		pCtx.close();
	}
	
	

}
