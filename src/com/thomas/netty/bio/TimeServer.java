package com.thomas.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**   
* @Description: TODO(用一句话描述该文件做什么) 
* @author thomas_liu  
* @date 2018年6月14日 下午11:29:02 
* @version V1.0   
*/
public class TimeServer {
	
	public static void main(String[] args) throws IOException {
		int port = 8080;
		if(args != null && args.length >0){
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				//采用默认值
			}
		}
		
		ServerSocket server = null;
			
		try {
			server = new ServerSocket(port);
			System.out.println("The time server is start in port:"+port);
			Socket socket = null;
			while(true){
				socket = server.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
		} finally{
			if(server != null){
				System.out.println("The time server close");
				server.close();
				server = null;
			}
		}
	}
	
	
	
	
}
