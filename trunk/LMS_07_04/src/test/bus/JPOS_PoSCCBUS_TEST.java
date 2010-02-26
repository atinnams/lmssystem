package test.bus;

import org.hcmus.bus.JPOS_PoSCCBUS;

import junit.framework.TestCase;

public class JPOS_PoSCCBUS_TEST extends TestCase {

	public JPOS_PoSCCBUS_TEST(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCheckPoSCC() {
		int result = JPOS_PoSCCBUS.checkPoSCC("01");
		assertEquals("Expected is 1 but actual is " + Integer.toString(result),1,result);
		
	}

}
