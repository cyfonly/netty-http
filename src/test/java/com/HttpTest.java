package com;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.alibaba.fastjson.JSONObject;
import com.decoder.protobuf.UserProbuf;

/**
 * 测试类
 * @author yunfeng.cheng
 * @create 2016-07-25
 */
public class HttpTest {
	
	public static void main(String[] args) throws Exception{
		sendGet();
//		sendPostJson();
//		sendPostForm();
//		sendProtobuf();
	}
	
	private static void sendPostJson() throws Exception{
		String path = "http://127.0.0.1:8080";
		JSONObject obj = new JSONObject();
		obj.put("id", "10001");
		obj.put("name", "yunfengCheng");
		obj.put("sex", "M");
		String jsonStr = obj.toJSONString();
		byte[] data = jsonStr.getBytes();
        java.net.URL url = new java.net.URL(path);
        java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        OutputStream outStream = conn.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();
        if(conn.getResponseCode() == 200){
        	BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) conn.getInputStream(), "UTF-8"));
        	String msg = in.readLine();
        	System.out.println("msg: " + msg);
            in.close();
        }
        conn.disconnect();
	}
	
	private static void sendPostForm() throws Exception{
		String path = "http://127.0.0.1:8080/";
		String parm = "id=10001&name=yunfengCheng&sex=M";
		byte[] data = parm.getBytes();
        java.net.URL url = new java.net.URL(path);
        java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Connection", "close");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        OutputStream outStream = conn.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();
        if(conn.getResponseCode() == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) conn.getInputStream(), "UTF-8"));
            String msg = in.readLine();
        	System.out.println("msg: " + msg);
            in.close();
        }
        conn.disconnect();
	}
	
	private static void sendGet() throws Exception{
		String path = "http://127.0.0.1:8080/";
		String reqUrl = path + "?id=10001&name=yunfengCheng&sex=M";
        java.net.URL url = new java.net.URL(reqUrl);
        java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        conn.connect();
        if (conn.getResponseCode() == 200) {
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String msg = in.readLine();
	        System.out.println(msg);
	        in.close();
        }
        conn.disconnect();
	}
	
	private static void sendProtobuf() throws Exception{
		String path = "http://127.0.0.1:8080/";
		
		UserProbuf.User.Builder builder = UserProbuf.User.newBuilder();
		builder.setId(10001);
		builder.setName("yunfengCheng");
		builder.setSex(UserProbuf.User.Sex.M);
		UserProbuf.User user = builder.build();
		
		byte[] data = user.toByteArray();
		
		java.net.URL url = new java.net.URL(path);
		java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        OutputStream outStream = conn.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();
        if(conn.getResponseCode() == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) conn.getInputStream(), "UTF-8"));
            String msg = in.readLine();
        	System.out.println("msg: " + msg);
            in.close();
        }
        conn.disconnect();
	}

}
