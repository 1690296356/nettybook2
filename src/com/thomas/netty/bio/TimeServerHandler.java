package com.thomas.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**   
* @Description: TODO(用一句话描述该文件做什么) 
* @author thomas_liu  
* @date 2018年6月14日 下午11:50:01 
* @version V1.0   
*/
public class TimeServerHandler implements Runnable  {
	

	
	private Socket socket;
	
	public TimeServerHandler(Socket socket) {
		super();
		this.socket = socket;
	}


	public TimeServerHandler() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(
					this.socket.getInputStream()));
			
			out = new PrintWriter(this.socket.getOutputStream(), true);
			String currentTime = null;
			String body = null;
			while(true) {
				body = in.readLine();
				if(body == null)
					break;
				
				System.out.println("The time server receive order :"+body);
		currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(
			System.currentTimeMillis()).toString() : "BAD ORDER";
		out.println(currentTime);
				
			}
			
		} catch (IOException e) {
			if(in != null){
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			if(out != null){
				out.close();
				out = null;
			}
			
			if(this.socket != null){
				try {
					this.socket.close();
		} catch (IOException e1) {
		    e1.printStackTrace();
				}
			this.socket = null;
		}
		
		
	}
   }


	

}
