package com.thomas.netty.aio;

import java.io.BufferedReader;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**   
* @Description: AcceptCompletionHandler
* @author thomas_liu  
* @date 2018年8月4日 下午6:36:58 
* @version V1.0   
*/ 
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {

	@Override
	public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
		attachment.asynchronousServerSocketChannel.accept(attachment, this);
		 ByteBuffer buffer = ByteBuffer.allocate(1024); 
		 result.read(buffer, buffer, new ReadCompletionHandler(result));
	}

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable, java.lang.Object)
	 */
	@Override
	public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
		// TODO Auto-generated method stub
		
	} 
	
	
	  
	
}
