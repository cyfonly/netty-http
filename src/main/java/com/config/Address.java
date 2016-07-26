package com.config;

/**
 * mongo address demo
 * @author yunfeng.cheng
 * @create 2016-07-24
 */
public class Address {
	
	private String ip;
	private int port;
	
	public Address(String ip, int port){
		this.ip = ip;
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
