package test.bus;

import java.sql.Connection;
import java.sql.Statement;

import org.hcmus.bus.JPOS_CustomerBUS;
import org.hcmus.bus.JPOS_CustomerDTO;
import org.hcmus.dao.lms.DataProvider;

import junit.framework.Assert;
import junit.framework.TestCase;


public class JPOS_CustomerBUS_TEST extends TestCase {
	
	Connection con = null;
	
	public JPOS_CustomerBUS_TEST(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		String SQL = "update JPOS_Customer set JPOS_CurrentPoint = 150 where JPOS_IDCustomer=38";
		con = DataProvider.getConnection();
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
		JPOS_CustomerDTO customer = JPOS_CustomerBUS.getCustomer(38,con);
		Assert.assertEquals(customer.getJPOS_Barcode(), "1234567812345678");
		Assert.assertEquals(customer.getJPOS_CurrentPoint(), 150);
	}
	
	public void testAddPoint() {
		JPOS_CustomerDTO customer = new JPOS_CustomerDTO(38,"1234567812345678",0);
		JPOS_CustomerBUS.addPoint(customer,1, 150, "000000000000001","00000001","01",con);
		JPOS_CustomerDTO customerAssert = JPOS_CustomerBUS.getCustomer(38,con);
		Assert.assertEquals(customerAssert.getJPOS_CurrentPoint(), 300);
	}
	
	public void testSubPoint() {
		JPOS_CustomerDTO customer = new JPOS_CustomerDTO(38,"1234567812345678",0);
		JPOS_CustomerBUS.subtractPoint(customer,1, 100, "000000000000001","00000001","01",con);
		JPOS_CustomerDTO customerAssert = JPOS_CustomerBUS.getCustomer(38,con);
		Assert.assertEquals(customerAssert.getJPOS_CurrentPoint(), 50);
	}
}
