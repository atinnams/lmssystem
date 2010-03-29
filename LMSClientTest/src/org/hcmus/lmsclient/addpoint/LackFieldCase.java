package org.hcmus.lmsclient.addpoint;

import org.hcmus.lmsclient.util.Constant;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.GenericPackager;

import junit.framework.TestCase;

/**
 * 
 * @author HUNGPT
 * Purpose of this case used to check client send  
 * lack bundle of field. In this case, client didn’t send field Number 11. 
 */
public class LackFieldCase extends TestCase {

	public LackFieldCase(String name) {
		super(name);
	}
	
	public void testLackField(){
		try {
			// set package iso87binary.xml
			ISOPackager p = new GenericPackager("cfg/iso87binary.xml");

			// create and set field for message
			ISOMsg m = new ISOMsg();
			m.setMTI("0200");
			m.set("3", "407000");
			m.set("4", "000001500000");
			m.set("12", "114109");
			m.set("13", "0122");
			m.set("25", "03");
			m.set("35", "1234567812345678=10041011765710800000");
			m.set("41", "00000001");
			m.set("42", "000000000000001");

			// set package for message
			m.setPackager(p);

			// set channel for message with server address, port and package
			ISOChannel channel = new ASCIIChannel("localhost", 9800, p);

			// connect to loyalty manage system server
			channel.connect();

			// send message
			channel.send(m);

			// receive response
			ISOMsg r = channel.receive();

			// logic
			if (!r.hasField(62) && !r.hasField(39)) {
				fail();
			}
			assertEquals("15",r.getValue(39));
			assertEquals(Constant.INVALID_FIELD,r.getValue(62));
			// close connection
			channel.disconnect();

		} catch (Exception ex) {
			fail("Some exceptions founded.");
		}
	}
}
