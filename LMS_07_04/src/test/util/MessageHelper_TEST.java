package test.util;

import junit.framework.TestCase;

import org.hcmus.Util.MessageHelper;
import org.jpos.iso.ISOMsg;

public class MessageHelper_TEST extends TestCase {

	ISOMsg msg = null;
	public MessageHelper_TEST(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		msg = new ISOMsg();
		msg.set("35","1234567812345678=10041011765710800000");
		msg.set("63","000000000000000000000000000150000000000000000000000000000150");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetCardId() {
		String cardid = MessageHelper.getCardId(msg);
		assertEquals("Expected is 150 but actual is" + cardid,"1234567812345678",cardid);
	}

	
	public void testFormat(){
		String format = MessageHelper.format("10", 4);
		System.out.println(format);
		assertEquals("0010", format);
	}
}
