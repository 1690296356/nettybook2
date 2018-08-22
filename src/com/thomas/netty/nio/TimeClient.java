package com.thomas.netty.nio;

/**   
* @Description: TODO(用一句话描述该文件做什么) 
* @author thomas_liu  
* @date 2018年6月19日 上午10:27:11 
* @version V1.0   
*/
public class TimeClient {
	
	public static void main(String[] args) {
		
		int port = 8080;
		if(args != null && args.length>0){
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				//采用默认值
			}
		}
		
		new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();
		
	}
	
	
	
}
