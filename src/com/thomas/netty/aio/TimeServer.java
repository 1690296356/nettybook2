package com.thomas.netty.aio;

import java.io.IOException;

/**   
* @Description: aio时间服务器服务端
* @author thomas_liu  
* @date 2018年8月4日 下午5:39:08 
* @version V1.0   
*/
public class TimeServer {
	
	public static void main(String[] args) throws IOException {
		int port = 8080;
		if(args != null && args.length >0){
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				// 采用默认值
			}
		}
		
		AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
		new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
		
	}
	

}
