package org.hcmus.Gateway;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;

public class HEXGatewayISOListener extends GatewayISOListener {

	String name;

	public HEXGatewayISOListener() {
		super("HEX");
	}
	
	public HEXGatewayISOListener(String name){
		super(name);
	}
	
	public boolean process(ISOSource source, ISOMsg msg) {
		return super.process(source, msg);
	}

}