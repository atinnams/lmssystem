package org.hcmus.client;

public class ConfigManager {
	static String ip = "";
	static int port = 0;
	static String mid = "";
	static String tid = "";
	
	public static String getIp() {
		return ip;
	}
	
	public static void setIp(String ip) {
		ConfigManager.ip = ip;
	}
	
	public static int getPort() {
		return port;
	}
	
	public static void setPort(int port) {
		ConfigManager.port = port;
	}
	
	public static String getMid() {
		return mid;
	}
	
	public static void setMid(String mid) {
		ConfigManager.mid = mid;
	}
	
	public static String getTid() {
		return tid;
	}
	
	public static void setTid(String tid) {
		ConfigManager.tid = tid;
	}
	
	public static String getString(){
		return ConfigManager.getIp() + ConfigManager.getPort() + ConfigManager.getMid() + ConfigManager.getTid();
	}
}
