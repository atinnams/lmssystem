package org.hcmus.lmsclient.subtractpoint;

import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.GenericPackager;

import junit.framework.TestCase;

public class SubtractPointCase extends TestCase {

	public SubtractPointCase(String name) {
		super(name);
	}
	
	public void testSubtractHappyPath(){
try {
			
			//set package iso87binary.xml
			ISOPackager p = new GenericPackager("cfg/iso87binary.xml");
			
			//create and set field for message
			ISOMsg m = new ISOMsg();
			m.setMTI("0200");
			m.set("3", "417000");
			m.set("4", "000001500000");
			m.set("11", "123456");
			m.set("12", "114109");
			m.set("13", "0122");
			m.set("25", "00");
			m.set("35", "1234567812345678=10041011765710800000");
			m.set("41", "00000001");
			m.set("42", "000000000000001");
			
			//set package for message
			m.setPackager(p);
			
			//set channel for message with server address, port and package
			ISOChannel channel = new ASCIIChannel("localhost", 9800, p);
			
			
			//connect to loyalty manage system server
			channel.connect();
			
			//send message
			channel.send(m);
			
			//receive response
			ISOMsg r = channel.receive();
			
			//logic
			if(!r.hasField(63)){
				fail();
			}
			assertEquals("000000000000000000000000000015000000000000000000000000000015", r.getValue(63));
			
			//close connection
			channel.disconnect();
			
		} catch (Exception ex) {
			fail("Some exceptions founded.");
		}
	}
}
