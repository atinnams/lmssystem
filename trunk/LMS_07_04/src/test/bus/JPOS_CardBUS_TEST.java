package test.bus;

import org.hcmus.bus.JPOS_CardBUS;

import junit.framework.TestCase;

public class JPOS_CardBUS_TEST extends TestCase {

	public JPOS_CardBUS_TEST(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCheckCard() {
		int result = JPOS_CardBUS.checkCard("1111111111111111");
		assertEquals("Expected to 1 but actual " + Integer.toString(result),1, result);
		int resultZero = JPOS_CardBUS.checkCard("0000000000000000");
		assertEquals("Expected to 0 but actual " + Integer.toString(result),0, resultZero);
	}

	public void testCheckExpire() {
		int result = JPOS_CardBUS.checkExpire("1111111111111111");
		assertEquals("Expected to 1 but actual " + Integer.toString(result),1, result);
		int resultZero = JPOS_CardBUS.checkExpire("1234567812345678");
		assertEquals("Expected to 0 but actual " + Integer.toString(result),0, resultZero);
	}

}
