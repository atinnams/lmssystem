package test.bus;

import org.hcmus.bus.JPOS_MerchantBUS;

import junit.framework.TestCase;

public class JPOS_MerchantBUS_TEST extends TestCase {

	public JPOS_MerchantBUS_TEST(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCheckMerchant() {
		int result = JPOS_MerchantBUS.checkMerchant("000000000000001","00000001");
		assertEquals("Expected is 1 but actual is" + Integer.toString(result), 1, result);
	}

}
