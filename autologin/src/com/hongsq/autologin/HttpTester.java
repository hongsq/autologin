/**
 * 
 */
package com.hongsq.autologin;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author hongshuiqiao
 *
 */
public class HttpTester {

	public static void main(String[] args) {
		
		
		String url = "";
		
		HttpURLConnection connection = null;
		try {
			URL httpURL = new URL(url);
			connection = (HttpURLConnection) httpURL.openConnection();
			
			/*****************HttpURLConnection对象参数**********************************/
			// 设置是否向connection输出，因为这个是post请求，参数要放在   
			// http正文内，因此需要设为true, 默认情况下是false;   
			connection.setDoOutput(true);   

			// 设置是否从connection读入，默认情况下是true;   
			connection.setDoInput(true);   
			 
			// Post 请求不能使用缓存   
			connection.setUseCaches(false);   

			// 设定传送的内容类型是可序列化的java对象   
			// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)   
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");   

			// 设定请求的方法为"POST"，默认是GET   
			connection.setRequestMethod("POST");   

			// 连接，从上述第2条中url.openConnection()至此的配置必须要在connect之前完成，   
			connection.connect();  
			
			
			/*********************HttpURLConnection连接******************************/
			// 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，   
			// 所以在开发中不调用上述的connect()也可以)。
			OutputStream output = connection.getOutputStream();
			
			/******************HttpURLConnection写数据与发送数据**********************/
			// 现在通过输出流对象构建对象输出流对象，以实现输出可序列化的对象。   
			ObjectOutputStream objOutputStrm = new ObjectOutputStream(output);   

			// 向对象输出流写出数据，这些数据将存到内存缓冲区中   
			objOutputStrm.writeObject(new String("我是测试数据"));   

			// 刷新对象输出流，将任何字节都写入潜在的流中（些处为ObjectOutputStream）   
			objOutputStrm.flush();   

			// 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,   
			// 在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器   
			objOutputStrm.close();   

			// 调用HttpURLConnection连接对象的getInputStream()函数,   
			// 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。   
			InputStream input = connection.getInputStream(); // <===注意，实际发送请求的代码段就在这里   

			
			
			
			
			// 上边的httpConn.getInputStream()方法已调用,本次HTTP请求已结束,下边向对象输出流的输出已无意义，   
			// 既使对象输出流没有调用close()方法，下边的操作也不会向对象输出流写入任何数据.   
			// 因此，要重新发送数据时需要重新创建连接、重新设参数、重新创建流对象、重新写数据、   
			// 重新发送数据(至于是否不用重新这些操作需要再研究)   
			//objOutputStrm.writeObject(new String(""));   
			//connection.getInputStream();
			
			
			/**
			 * 
HttpURLConnection是基于HTTP协议的，其底层通过socket通信实现。如果不设置超时（timeout），在网络异常的情况下，可能会导致程序僵死而不继续往下执行。可以通过以下两个语句来设置相应的超时： 
System.setProperty("sun.net.client.defaultConnectTimeout", 超时毫秒数字符串); 
System.setProperty("sun.net.client.defaultReadTimeout", 超时毫秒数字符串); 

其中： sun.net.client.defaultConnectTimeout：连接主机的超时时间（单位：毫秒） 
sun.net.client.defaultReadTimeout：从主机读取数据的超时时间（单位：毫秒）
			 * 
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != connection)
				connection.disconnect();
		}
	}
	
}
