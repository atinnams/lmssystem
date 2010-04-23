package test.bus;

import java.sql.Connection;

import junit.framework.TestCase;

import org.hcmus.bus.JPOS_MerchantBUS;
import org.hcmus.dao.lms.DataProvider;

public class JPOS_MerchantBUS_TEST extends TestCase {

	private Connection con = null;
	public JPOS_MerchantBUS_TEST(String name) {
		super(name);
		con = DataProvider.getConnection();
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCheckMerchant() {
		int result = JPOS_MerchantBUS.checkMerchant("000000000000001","00000001",con);
		assertEquals("Expected is 1 but actual is" + Integer.toString(result), 1, result);
	}

}
