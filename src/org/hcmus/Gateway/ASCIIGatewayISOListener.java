package org.hcmus.Gateway;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;

public class ASCIIGatewayISOListener extends GatewayISOListener {

	String name;

	public ASCIIGatewayISOListener() {
		super("ASCII");
	}
	
	public ASCIIGatewayISOListener(String name){
		super(name);
	}
	
	public boolean process(ISOSource source, ISOMsg msg) {
		return super.process(source, msg);
	}

}
