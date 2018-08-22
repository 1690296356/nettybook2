package com.thomas.netty.aio;

/**   
* @Description: TimeClient
* @author thomas_liu  
* @date 2018年8月6日 上午12:52:51 
* @version V1.0   
*/
public class TimeClient {

    /**
     * @param args
     */
    public static void main(String[] args) {
	int port = 8080;
	if (args != null && args.length > 0) {
	    try {
		port = Integer.valueOf(args[0]);
	    } catch (NumberFormatException e) {
		// 采用默认值
	    }

	}
	new Thread(new AsyncTimeClientHandler("127.0.0.1", port),
		"AIO-AsyncTimeClientHandler-001").start();

    }
}
