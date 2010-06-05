package test.dao;

import java.sql.Connection;

import org.hcmus.dao.lms.JPOS_LogDAO;

import junit.framework.TestCase;

public class JPOS_LogDAO_TEST extends TestCase {
Connection con = null;
	
	public JPOS_LogDAO_TEST(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		con = DataProviderTest.getConnection();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		if(con != null) {
			con.close();
		}
	}
	
	public void testBalanceLog(){
		JPOS_LogDAO logDAO = new JPOS_LogDAO();
		logDAO.balanceInquiryLog("9704215000000046", 4, 10000,"000000000000001", "00000001", "01", con);
	}
	
	public void testVoidLog(){
		JPOS_LogDAO logDAO = new JPOS_LogDAO();
		logDAO.addVoidLog("9704215000000046", 3, 10000,"052514412207","000000000000001", "00000001", "01", con);
	}
}
