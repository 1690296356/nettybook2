package com.thomas.netty.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;

public class TimeServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext pCtx, Object pMsg) {
		ByteBuf buf = (ByteBuf)pMsg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, StandardCharsets.UTF_8);
		System.out.println("The time server receive order :"+ body);
		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
				new java.util.Date(System.currentTimeMillis()).toString() :"BAD ORDER";
				
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		pCtx.write(resp);
		
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext pCtx) {
		pCtx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext pCtx, Throwable pCause) {
		pCtx.close();
	}
	
	
	
}
