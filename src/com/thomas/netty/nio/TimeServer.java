package com.thomas.netty.nio;

/**   
* @Description: TODO(用一句话描述该文件做什么) 
* @author thomas_liu  
* @date 2018年6月18日 下午10:40:54 
* @version V1.0   
*/
public class TimeServer {
	
	public static void main(String[] args) {

		int port = 8080;
		if(args != null && args.length>0){
			try {
				port = Integer.valueOf(args[0]);
	    } catch (NumberFormatException e) {
				//采用默认值
			}
		}
		
		MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
		new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
		
	}

}
