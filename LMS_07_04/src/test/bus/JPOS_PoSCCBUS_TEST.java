package test.bus;

import java.sql.Connection;

import junit.framework.TestCase;

import org.hcmus.bus.JPOS_PoSCCBUS;
import org.hcmus.dao.lms.DataProvider;

public class JPOS_PoSCCBUS_TEST extends TestCase {

	private Connection con = null;
	public JPOS_PoSCCBUS_TEST(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		con = DataProvider.getConnection();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCheckPoSCC() {
		int result = JPOS_PoSCCBUS.checkPoSCC("01",con);
		assertEquals("Expected is 1 but actual is " + Integer.toString(result),1,result);
	}

}
