package test.dao;

import java.sql.Connection;
import java.sql.Statement;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.hcmus.bus.JPOS_CustomerDTO;
import org.hcmus.dao.lms.JPOS_CustomerDAO;


public class JPOS_CustomerDAO_TEST extends TestCase {
	
	Connection con = null;
	
	public JPOS_CustomerDAO_TEST(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		String SQL = "update JPOS_Customer set JPOS_CurrentPoint = 150 where JPOS_CustomerID=1";
		con = DataProviderTest.getConnection();
		Statement st = con.createStatement();
		st.execute(SQL);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		if(con != null) {
			con.close();
		}
	}
	
	public void testGetCustomer() {
		JPOS_CustomerDAO customerDAO = new JPOS_CustomerDAO();
		JPOS_CustomerDTO customer = customerDAO.getCustomer(1,con);
		Assert.assertEquals(customer.getJPOS_CurrentPoint(), 150);
	}
}
