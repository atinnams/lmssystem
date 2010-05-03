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
	
	public void testAddNormalPoint() {
		JPOS_CustomerDAO customerDAO = new JPOS_CustomerDAO();
		int result = customerDAO.addNormalPoint("1234567812345678",1, 150, "000000000000001","00000001","01",con);
		JPOS_CustomerDTO customerAssert = customerDAO.getCustomer(1,con);
		Assert.assertEquals(customerAssert.getJPOS_CurrentPoint(), 300);
		System.out.println(result);
		if(result == 0){
			fail("Result must not to be zero.");
		}
	}
	
	
	public void testSubPoint() {
		JPOS_CustomerDAO customerDAO = new JPOS_CustomerDAO();
		int result = customerDAO.subtractPoint("1234567812345678",2, 100, "000000000000001","00000001","01",con);
		JPOS_CustomerDTO customerAssert = customerDAO.getCustomer(1,con);
		Assert.assertEquals(customerAssert.getJPOS_CurrentPoint(), 50);
		if(result == 0){
			fail();
		}
	}
	
	public void testGiftPoint(){
		JPOS_CustomerDAO customerDAO = new JPOS_CustomerDAO();
		int result = customerDAO.checkRedemptionPoint("1234567812345678", 200, con);
		Assert.assertEquals(0,result);
		
		result = customerDAO.checkRedemptionPoint("1234567812345678", 10, con);
		Assert.assertEquals(1,result);
		
		if(result == 0){
			fail();
		}
	}
}
