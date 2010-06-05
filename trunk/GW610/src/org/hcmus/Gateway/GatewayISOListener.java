package org.hcmus.Gateway;

import java.io.FileOutputStream;
import java.io.IOException;

import org.hcmus.Gateway.util.GWLogSource;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ISOUtil;

public class GatewayISOListener implements ISORequestListener {

	String name;
	FileOutputStream f;

	public GatewayISOListener() {
		name = "NAC";
	}
	
	public GatewayISOListener(String name){
		this.name = name;
	}

	@Override
	public boolean process(ISOSource source, ISOMsg msg) {
		
		try {
			if(f != null){
				f.close();
			}
			
			Sender sender = (Sender)Sender.getSender("GW610");
			
			if (sender != null) {
				
				ISOMsg response = sender.Request(msg);
				source.send(response);
				
				//log to file
				GWLogSource gwLog = GWLogSource.getLogSource("GW610");
				gwLog.printHexValue(name + "-message-receive", ISOUtil.hexString(msg.pack()));
				gwLog.printHexValue(name + "-message-send", ISOUtil.hexString(response.pack()));
				
			} else {
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ISOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
