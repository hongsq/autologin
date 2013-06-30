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
			
			/*****************HttpURLConnection�������**********************************/
			// �����Ƿ���connection�������Ϊ�����post���󣬲���Ҫ����   
			// http�����ڣ������Ҫ��Ϊtrue, Ĭ���������false;   
			connection.setDoOutput(true);   

			// �����Ƿ��connection���룬Ĭ���������true;   
			connection.setDoInput(true);   
			 
			// Post ������ʹ�û���   
			connection.setUseCaches(false);   

			// �趨���͵����������ǿ����л���java����   
			// (����������,�ڴ������л�����ʱ,��WEB����Ĭ�ϵĲ�����������ʱ������java.io.EOFException)   
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");   

			// �趨����ķ���Ϊ"POST"��Ĭ����GET   
			connection.setRequestMethod("POST");   

			// ���ӣ���������2����url.openConnection()���˵����ñ���Ҫ��connect֮ǰ��ɣ�   
			connection.connect();  
			
			
			/*********************HttpURLConnection����******************************/
			// �˴�getOutputStream�������Ľ���connect(������ͬ���������connect()������   
			// �����ڿ����в�����������connect()Ҳ����)��
			OutputStream output = connection.getOutputStream();
			
			/******************HttpURLConnectionд�����뷢������**********************/
			// ����ͨ����������󹹽����������������ʵ����������л��Ķ���   
			ObjectOutputStream objOutputStrm = new ObjectOutputStream(output);   

			// ����������д�����ݣ���Щ���ݽ��浽�ڴ滺������   
			objOutputStrm.writeObject(new String("���ǲ�������"));   

			// ˢ�¶�������������κ��ֽڶ�д��Ǳ�ڵ����У�Щ��ΪObjectOutputStream��   
			objOutputStrm.flush();   

			// �ر������󡣴�ʱ������������������д���κ����ݣ���ǰд������ݴ������ڴ滺������,   
			// �ڵ����±ߵ�getInputStream()����ʱ�Ű�׼���õ�http������ʽ���͵�������   
			objOutputStrm.close();   

			// ����HttpURLConnection���Ӷ����getInputStream()����,   
			// ���ڴ滺�����з�װ�õ�������HTTP������ķ��͵�����ˡ�   
			InputStream input = connection.getInputStream(); // <===ע�⣬ʵ�ʷ�������Ĵ���ξ�������   

			
			
			
			
			// �ϱߵ�httpConn.getInputStream()�����ѵ���,����HTTP�����ѽ���,�±�����������������������壬   
			// ��ʹ���������û�е���close()�������±ߵĲ���Ҳ��������������д���κ�����.   
			// ��ˣ�Ҫ���·�������ʱ��Ҫ���´������ӡ���������������´�������������д���ݡ�   
			// ���·�������(�����Ƿ���������Щ������Ҫ���о�)   
			//objOutputStrm.writeObject(new String(""));   
			//connection.getInputStream();
			
			
			/**
			 * 
HttpURLConnection�ǻ���HTTPЭ��ģ���ײ�ͨ��socketͨ��ʵ�֡���������ó�ʱ��timeout�����������쳣������£����ܻᵼ�³�����������������ִ�С�����ͨ���������������������Ӧ�ĳ�ʱ�� 
System.setProperty("sun.net.client.defaultConnectTimeout", ��ʱ�������ַ���); 
System.setProperty("sun.net.client.defaultReadTimeout", ��ʱ�������ַ���); 

���У� sun.net.client.defaultConnectTimeout�����������ĳ�ʱʱ�䣨��λ�����룩 
sun.net.client.defaultReadTimeout����������ȡ���ݵĳ�ʱʱ�䣨��λ�����룩
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
