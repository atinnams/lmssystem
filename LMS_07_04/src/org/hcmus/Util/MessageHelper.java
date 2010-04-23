package org.hcmus.Util;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

public class MessageHelper {
	
	public static int getTotalPoint(ISOMsg msg) {
		int result = 0;
		String strPoint = "";
		try {
			String field63 = (String) msg.getValue(63);
			if (field63.isEmpty())
				return -1;
			if (field63.length() != 60) {
				return -1;
			}

			for (int i = 0; i < 5; i++) {
				strPoint = field63.substring(i * 10, (i + 1) * 10);
				result += Integer.parseInt(strPoint);
			}

			strPoint = field63.substring(51, 60);

			// checksum for point
			if (result != Integer.parseInt(strPoint)) {
				return -1;
			}

		} catch (ISOException e) {
			e.printStackTrace();
			return -1;
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			return -1;
		}

		return result;
	}

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
	

	public static String pointToStringField63(int rangePoint,int promotionPoint,int frequencyPoint,int birthdayPoint,int joinPoint) {
		String result = "";
		int totalPoint = rangePoint + promotionPoint + frequencyPoint + birthdayPoint + joinPoint;
		result += MessageHelper.format(Integer.toString(rangePoint));
		result += MessageHelper.format(Integer.toString(promotionPoint));
		result += MessageHelper.format(Integer.toString(frequencyPoint));
		result += MessageHelper.format(Integer.toString(birthdayPoint));
		result += MessageHelper.format(Integer.toString(joinPoint));
		result += MessageHelper.format(Integer.toString(totalPoint));
		return result;
	}
	
	public static int getPoint(ISOMsg msg) {
		int result = 0;
		try {
			String strPoint = (String)msg.getValue(4);
			NormalRule normalRule = (NormalRule)NameRegistrar.get("normal.rule");
			String amount = normalRule.getAmount();
			String point = normalRule.getPoint();
			result = (Integer.parseInt(strPoint) / Integer.parseInt(amount)) * Integer.parseInt(point) ;
		} catch (ISOException e) {
			e.printStackTrace();
			result = -1;
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
		try {
			String strAmount = (String)msg.getValue(4);
			result = Integer.parseInt(strAmount);
		} catch (ISOException e) {
			e.printStackTrace();
			result = -1;
		} catch(NumberFormatException ex) {
			ex.printStackTrace();
			result = -1;
		}
		
		return result;
	}
	
	public static int getGiftType(ISOMsg msg){
		int result = 0;
		try {
			String strPoint = (String)msg.getValue(4);
			result = Integer.parseInt(strPoint);
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
}
