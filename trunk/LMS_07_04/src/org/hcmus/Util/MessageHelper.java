package org.hcmus.Util;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

public class MessageHelper {
	
	public static String getCardId(ISOMsg msg) {
		String result = "";
		try {
			String field35 = (String) msg.getValue(35);
			result = field35.substring(0, 16);
		} catch (ISOException ex) {
			ex.printStackTrace();
		}
		return result;

	}
	
	public static String getTID(ISOMsg msg) {
		String field41 = "";
		try {
			field41 = (String)msg.getValue(41);
		} catch (ISOException ex) {
			ex.printStackTrace();
		}
		return field41;
	}
	
	public static String getMID(ISOMsg msg) {
		String field42 = "";
		try {
			field42 = (String)msg.getValue(42);
		} catch (ISOException ex) {
			ex.printStackTrace();
		}
		return field42;
	}
	
	public static String getPoSCC(ISOMsg msg) {
		String field25 = "";
		try {
			field25 = (String)msg.getValue(25);
		} catch (ISOException ex) {
			ex.printStackTrace();
		}
		return field25;
	}
	
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
	
	public static int getPoint(ISOMsg msg){
		int result = 0;
		String field61;
		try {
			field61 = ISOUtil.hexString(msg.getComponent(61).getBytes());
			String money = field61.substring(6, 14);
			result = MessageHelper.getPoint(money);
		} catch (ISOException e) {
			result = -1;
			e.printStackTrace();
		}
		return result;
	}
	
	public static int getPoint(String strPoint) {
		int result = 0;
		try {
			NormalRule normalRule = (NormalRule)NameRegistrar.get("normal.rule");
			String amount = normalRule.getAmount();
			String point = normalRule.getPoint();
			result = (Integer.parseInt(strPoint) / Integer.parseInt(amount)) * Integer.parseInt(point) ;
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			result = -1;
		} catch (NotFoundException e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}
	
	public static int getAmount(ISOMsg msg){
		int result = 0;
		String field61 = "";
		try {
			field61 = ISOUtil.hexString(msg.getComponent(61).getBytes());
			String money = field61.substring(6, 14);
			result = Integer.parseInt(money);
		} catch (ISOException e) {
			e.printStackTrace();
			result = -1;
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			result = -1;
		}
		
		return result;
	}
	
	public static int getGiftPoint(ISOMsg msg){
		int result = 0;
		String field48 = "";
		try {
			field48 = ISOUtil.hexString(msg.getComponent(48).getBytes());
			int index = field48.indexOf("FF21");
			if(index != -1){
				String strPoint = field48.substring(index+4, index+10);
				result = Integer.parseInt(strPoint);
			}else{
				return -1;
			}
			
		} catch (ISOException e) {
			e.printStackTrace();
			result = -1;
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			result = -1;
		}
		return result;
	}
	
	public static String makeTLV(String tag,String value){
		String result = "";
		int length = value.length();
		result = tag + ConvertHelper.decToHex(length) + ConvertHelper.asciiToHex(value);
		return result;
	}
	
	public static String getTransactionName(ISOMsg msg){
		String field48 = "";
		try {
			field48 = ISOUtil.hexString(msg.getComponent(48).getBytes());
			int index = field48.indexOf("FF21");
			if(index != -1){
				return field48.substring(index+4, index+10);
			}else{
				return null;
			}
		} catch (ISOException e) {
			return null;
		}
	}
}
