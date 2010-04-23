package test.dao;

import java.sql.Connection;

import junit.framework.TestCase;

import org.hcmus.bus.JPOS_MerchantBUS;
import org.hcmus.dao.lms.DataProvider;
import org.hcmus.dao.lms.JPOS_MerchantDAO;

public class JPOS_MerchantDAO_TEST extends TestCase {

	private Connection con = null;
	public JPOS_MerchantDAO_TEST(String name) {
		super(name);
		con = DataProviderTest.getConnection();
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCheckMerchant() {
		JPOS_MerchantDAO merchant = new JPOS_MerchantDAO();
		int result = merchant.checkMerchant("222            ","2       ",con);
		assertEquals("Expected is 1 but actual is" + Integer.toString(result), 1, result);
	}

}
