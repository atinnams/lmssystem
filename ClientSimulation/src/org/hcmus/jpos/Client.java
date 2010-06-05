package org.hcmus.jpos;

import org.hcmus.util.Constant;
import org.hcmus.util.MessageHelper;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.channel.HEXChannel;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;

public class Client {
	
	public Client(){}
	
	public static String redeem(String cardId,int amount,String host,int port,String tid,String mid){
		try {
			// set package iso87binary.XML
			ISOPackager p = new GenericPackager("cfg/edc2_iso87HEX.xml");
			
			// create and set field for message
			ISOMsg m = new ISOMsg();
			m.setMTI("0200");
			m.set("3", "888888");
			m.set("11", "123456");
			cardId = cardId + "=10041011765710800000";
			m.set("35",cardId);
			m.set("41",tid);
			m.set("42", mid);
			String invoice = MessageHelper.generateInvoice();
			String field48 = "FF21027207FF2203000000FF25091655D7C5A50A1B2C7BFF2F06" + invoice;
			m.set(48,ISOUtil.hex2byte(field48));
			
			String field61 = "FF3C14" + MessageHelper.format(Integer.toString(amount), 8) + "00000000000000000000000000000000";
			m.set(61,ISOUtil.hex2byte(field61));
			
			// set package for message
			m.setPackager(p);
			
			// set channel for message with server address, port and package
			ISOChannel channel = new HEXChannel(host,port,p,ISOUtil.hex2byte("6000010002"));

			// connect to loyalty manage system server
			channel.connect();

			// send message
			channel.send(m);

			// receive response
			ISOMsg r = channel.receive();
			
			channel.disconnect();
			
			String result = "";
			if(r != null){
				int rc = Integer.parseInt((String)r.getValue(39));
				switch(rc){
				case 0:
					String ff3eValue = MessageHelper.getFF3EValue(r);
					int value = Integer.parseInt(ff3eValue);
					
					result += "Redeem Transaction is sucessful:\r\n";
					result += "Invoice Id : " + invoice + "\r\n";
					result += "Total amount : " + Integer.toString(value) + "\r\n";
					
					String ff41Value = MessageHelper.getFF41Value(r);
					value = Integer.parseInt(ff41Value);
					
					result += "Redeem Amount: " + Integer.toString(value) + "\r\n";
					
					String ff58Value = MessageHelper.getFF58Value(r);
					value = Integer.parseInt(ff58Value);
					
					result += "Total Point: " + Integer.toString(value);
					
					break;
				case 14 :
					result = Constant.CARD_NOT_FOUND;
					break;
				case 54 :
					result = Constant.EXPIRE_CARD;
					break;
				case 15:
					result = Constant.INVALID_FIELD;
					break;
				case 3 :
					result = Constant.MID_OR_TID_NOT_FOUND;
					break;
				case 58:
					result = Constant.POSCC_NOT_FOUND;
					break;
				case 24:
					result = Constant.FORWARD_FAIL;
					break;
				case 93 :
					result = Constant.NO_ACTIVATED_CARD;
					break;
				case 16 :
					result = Constant.NOT_ENOUGH_MONEY;
					break;
				case 98 :
					result = Constant.TRANSACTION_REVERSAL;
					break;
				default :
					result = Constant.OTHER_ERROR;
					break; 
				}
			}else{
				result = "Cant not connect to server.";
			}
			
			return result;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static String reload(String cardId,int amount,String host,int port,String tid,String mid){
		try {
			// set package iso87binary.XML
			ISOPackager p = new GenericPackager("cfg/edc2_iso87HEX.xml");
			
			// create and set field for message
			ISOMsg m = new ISOMsg();
			m.setMTI("0200");
			m.set("3", "888888");
			m.set("11", "123456");
			cardId = cardId + "=10041011765710800000";
			m.set("35",cardId);
			m.set("41",tid);
			m.set("42", mid);
			String invoiceId = MessageHelper.generateInvoice();
			String field48 = "FF21027707FF2203000000FF2B05" + MessageHelper.format(Integer.toString(amount), 10) + "FF2F06" + invoiceId;
			m.set(48,ISOUtil.hex2byte(field48));
			
			// set package for message
			m.setPackager(p);
			
			// set channel for message with server address, port and package
			ISOChannel channel = new HEXChannel(host,port,p,ISOUtil.hex2byte("6000010002"));

			// connect to loyalty manage system server
			channel.connect();

			// send message
			channel.send(m);

			// receive response
			ISOMsg r = channel.receive();
			
			channel.disconnect();
			
			String result = "";
			if(r != null){
				int rc = Integer.parseInt((String)r.getValue(39));
				switch(rc){
				case 0:
					String ff3eValue = MessageHelper.getFF3EValue(r);
					int value = Integer.parseInt(ff3eValue);
					
					result += "Reload transaction is sucessful:\r\n";
					result += "Invoice ID: " + invoiceId + "\r\n";
					result += "Total amount : " + Integer.toString(value) + "\r\n";
					
					String ff41Value = MessageHelper.getFF41Value(r);
					value = Integer.parseInt(ff41Value);
					
					result += "Reload: " + Integer.toString(value) + "\r\n";
					
					break;
				case 14 :
					result = Constant.CARD_NOT_FOUND;
					break;
				case 54 :
					result = Constant.EXPIRE_CARD;
					break;
				case 15:
					result = Constant.INVALID_FIELD;
					break;
				case 3 :
					result = Constant.MID_OR_TID_NOT_FOUND;
					break;
				case 58:
					result = Constant.POSCC_NOT_FOUND;
					break;
				case 24:
					result = Constant.FORWARD_FAIL;
					break;
				case 93 :
					result = Constant.NO_ACTIVATED_CARD;
					break;
				case 18 :
					result = Constant.NOT_ENOUGH_MONEY;
					break;
				default :
					result = Constant.OTHER_ERROR;
					break; 
				}
			}else{
				result = "Cant not connect to server.";
			}
			
			return result;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static String voidRedeem(String cardId,String invoiceId,int amount,String host,int port,String tid,String mid){
		try {
			// set package iso87binary.XML
			ISOPackager p = new GenericPackager("cfg/edc2_iso87HEX.xml");
			
			// create and set field for message
			ISOMsg m = new ISOMsg();
			m.setMTI("0200");
			m.set("3", "888888");
			m.set("4", "000000000000");
			m.set("11", "123456");
			cardId = cardId + "=10041011765710800000";
			m.set("35",cardId);
			m.set("41",tid);
			m.set("42", mid);
			String invoiceGenerate = MessageHelper.generateInvoice();
			String field48 = "FF21027208FF2F06" + invoiceGenerate + "FF340F020072070000000000" + invoiceId;
			m.set(48,ISOUtil.hex2byte(field48));
			
			String field61 = "FF3C14" + MessageHelper.format(Integer.toString(amount), 8) + "00000000000000000000000000000000";
			m.set(61,ISOUtil.hex2byte(field61));
			
			// set package for message
			m.setPackager(p);
			
			// set channel for message with server address, port and package
			ISOChannel channel = new HEXChannel(host,port,p,ISOUtil.hex2byte("6000010002"));

			// connect to loyalty manage system server
			channel.connect();

			// send message
			channel.send(m);

			// receive response
			ISOMsg r = channel.receive();
			
			channel.disconnect();
			
			String result = "";
			if(r != null){
				int rc = Integer.parseInt((String)r.getValue(39));
				switch(rc){
				case 0:
					String ff3eValue = MessageHelper.getFF3EValue(r);
					int value = Integer.parseInt(ff3eValue);
					
					result += "Void Reddem transaction is sucessful:\r\n";
					result += "Invoice ID : " + invoiceId + "\r\n";
					result += "Total amount : " + Integer.toString(value) + "\r\n";
					
					String ff41Value = MessageHelper.getFF41Value(r);
					value = Integer.parseInt(ff41Value);
					
					result += "Reload: " + Integer.toString(value) + "\r\n";
					
					break;
				case 14 :
					result = Constant.CARD_NOT_FOUND;
					break;
				case 54 :
					result = Constant.EXPIRE_CARD;
					break;
				case 15:
					result = Constant.INVALID_FIELD;
					break;
				case 3 :
					result = Constant.MID_OR_TID_NOT_FOUND;
					break;
				case 58:
					result = Constant.POSCC_NOT_FOUND;
					break;
				case 24:
					result = Constant.FORWARD_FAIL;
					break;
				case 93 :
					result = Constant.NO_ACTIVATED_CARD;
					break;
				case 18 :
					result = Constant.NOT_ENOUGH_MONEY;
					break;
				default :
					result = Constant.OTHER_ERROR;
					break; 
				}
			}else{
				result = "Cant not connect to server.";
			}
			
			return result;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static String voidReload(String cardId,String invoiceId,int amount,String host,int port,String tid,String mid){
		try {
			// set package iso87binary.XML
			ISOPackager p = new GenericPackager("cfg/edc2_iso87HEX.xml");
			
			// create and set field for message
			ISOMsg m = new ISOMsg();
			m.setMTI("0200");
			m.set("3", "888888");
			m.set("4", "000000000000");
			m.set("11", "123456");
			cardId = cardId + "=10041011765710800000";
			m.set("35",cardId);
			m.set("41",tid);
			m.set("42", mid);
			String invoiceGenerate = MessageHelper.generateInvoice();
			String field48 = "FF21027708FF2F06" + invoiceGenerate + "FF340F020072070000000000" + invoiceId;
			m.set(48,ISOUtil.hex2byte(field48));
			
			String field61 = "FF3C14" + MessageHelper.format(Integer.toString(amount), 8) + "00000000000000000000000000000000";
			m.set(61,ISOUtil.hex2byte(field61));
			
			// set package for message
			m.setPackager(p);
			
			// set channel for message with server address, port and package
			ISOChannel channel = new HEXChannel(host,port,p,ISOUtil.hex2byte("6000010002"));

			// connect to loyalty manage system server
			channel.connect();

			// send message
			channel.send(m);

			// receive response
			ISOMsg r = channel.receive();
			
			channel.disconnect();
			
			String result = "";
			if(r != null){
				int rc = Integer.parseInt((String)r.getValue(39));
				switch(rc){
				case 0:
					String ff3eValue = MessageHelper.getFF3EValue(r);
					int value = Integer.parseInt(ff3eValue);
					
					result += "Void Reload transaction is sucessful:\r\n";
					result += "CardId :" + cardId + "\r\n";
					result += "Invoice ID : " + invoiceId + "\r\n";
					result += "Total amount : " + Integer.toString(value) + "\r\n";
					
					String ff41Value = MessageHelper.getFF41Value(r);
					value = Integer.parseInt(ff41Value);
					
					result += "Reload: " + Integer.toString(value) + "\r\n";
					
					break;
				case 14 :
					result = Constant.CARD_NOT_FOUND;
					break;
				case 54 :
					result = Constant.EXPIRE_CARD;
					break;
				case 15:
					result = Constant.INVALID_FIELD;
					break;
				case 3 :
					result = Constant.MID_OR_TID_NOT_FOUND;
					break;
				case 58:
					result = Constant.POSCC_NOT_FOUND;
					break;
				case 24:
					result = Constant.FORWARD_FAIL;
					break;
				case 93 :
					result = Constant.NO_ACTIVATED_CARD;
					break;
				case 18 :
					result = Constant.NOT_ENOUGH_MONEY;
					break;
				default :
					result = Constant.OTHER_ERROR;
					break; 
				}
			}else{
				result = "Cant not connect to server.";
			}
			
			return result;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static String balance(String cardId,String host,int port,String tid,String mid){
		try {
			// set package iso87binary.XML
			ISOPackager p = new GenericPackager("cfg/edc2_iso87HEX.xml");
			
			// create and set field for message
			ISOMsg m = new ISOMsg();
			m.setMTI("0200");
			m.set("3", "888888");
			m.set("11", "123456");
			cardId = cardId + "=10041011765710800000";
			m.set("35",cardId);
			m.set("41",tid);
			m.set("42", mid);
			String field48 = "FF21027567FF2203000000FF25091655D7C5A50A1B2C7BFF260414061231FF290720100519115436FF2F06051911543608FF450143FF4B06500000000000FF64080000000000000000";
			m.set(48,ISOUtil.hex2byte(field48));
			
			// set package for message
			m.setPackager(p);
			
			
			Logger logger = new Logger();
			logger.addListener (new SimpleLogListener (System.out));
			 
			// set channel for message with server address, port and package
			ISOChannel channel = new HEXChannel(host,port,p,ISOUtil.hex2byte("6000010002"));
			
			((LogSource)channel).setLogger (logger, "test-channel");

			// connect to loyalty manage system server
			channel.connect();

			// send message
			channel.send(m);

			// receive response
			ISOMsg r = channel.receive();
			
			channel.disconnect();
			
			String result = "";
			if(r != null){
				int rc = Integer.parseInt((String)r.getValue(39));
				switch(rc){
				case 0:
					String ff3eValue = MessageHelper.getFF3EValue(r);
					int value = Integer.parseInt(ff3eValue);
					
					result += "Balance transaction is sucessful:\r\n";
					result += "CardID :" + cardId + "\r\n";
					result += "Total amount :" + Integer.toString(value) + "\r\n";
					
					break;
				case 14 :
					result = Constant.CARD_NOT_FOUND;
					break;
				case 54 :
					result = Constant.EXPIRE_CARD;
					break;
				case 15:
					result = Constant.INVALID_FIELD;
					break;
				case 3 :
					result = Constant.MID_OR_TID_NOT_FOUND;
					break;
				case 58:
					result = Constant.POSCC_NOT_FOUND;
					break;
				case 24:
					result = Constant.FORWARD_FAIL;
					break;
				case 93 :
					result = Constant.NO_ACTIVATED_CARD;
					break;
				case 18 :
					result = Constant.NOT_ENOUGH_MONEY;
					break;
				default :
					result = Constant.OTHER_ERROR;
					break; 
				}
			}else{
				result = "Cant not connect to server.";
			}
			
			return result;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
