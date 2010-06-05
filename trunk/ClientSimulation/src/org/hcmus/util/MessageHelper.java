package org.hcmus.util;

import java.util.Random;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;


public class MessageHelper {
	
	public static String format(String s) {
		int length = s.length();
		if (length >= 10) {
			return s.substring(0,10);
		} else {
			StringBuffer buffer = new StringBuffer(5);
			for (int i = length; i < 10; i++) {
				buffer.append('0');
			}
			buffer.append(s);
			return buffer.toString();
		}
	}
	
	public static String format(String s,int num) {
		int length = s.length();
		if (length >= num) {
			return s.substring(0,num);
		} else {
			StringBuffer buffer = new StringBuffer();
			for (int i = length; i < num; i++) {
				buffer.append('0');
			}
			buffer.append(s);
			return buffer.toString();
		}
	}
	
	public static String getFF3EValue(ISOMsg msg){
		String field61;
		try {
			field61 = ISOUtil.hexString(msg.getComponent(61).getBytes());
			int index = field61.indexOf("FF3E");
			return field61.substring(40 + index,48+index);
		} catch (ISOException e) { 
			e.printStackTrace();
			return "";
		}
	}
	
	public static String getFF41Value(ISOMsg msg){
		String field61;
		try {
			field61 = ISOUtil.hexString(msg.getComponent(61).getBytes());
			int index = field61.indexOf("FF41");
			return field61.substring(40 + index,48+index);
		} catch (ISOException e) { 
			e.printStackTrace();
			return "";
		}
	}
	
	public static String getFF58Value(ISOMsg msg){
		String field61;
		try {
			field61 = ISOUtil.hexString(msg.getComponent(61).getBytes());
			int index = field61.indexOf("FF58");
			return field61.substring(6 + index,14 + index);
		} catch (ISOException e) { 
			e.printStackTrace();
			return "";
		}
	}
	
	public static String generateInvoice(){
		String result = "";
		Random rd = new Random();
		for(int i=0;i<12;i++){
			result += Integer.toString(rd.nextInt(9));
		}
		return result;
	}
	
	public static boolean isDigits(String str){
		int len = str.length();
		for(int i=0;i<len;i++){
			char a = str.charAt(i);
			if(a < '0' && a > '9'){
				return false;
			}
		}
		return true;
	}
}
