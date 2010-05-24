package test.dao;

import java.sql.Connection;

import junit.framework.TestCase;

import org.hcmus.dao.lms.JPOS_CardDAO;

public class JPOS_CardDAO_TEST extends TestCase {

	private Connection con = null;
	
	public JPOS_CardDAO_TEST(String name) {
		super(name);
		con = DataProviderTest.getConnection();
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCheckCard() {
		JPOS_CardDAO cardDAO = new JPOS_CardDAO();
		int result = cardDAO.checkCard("1234567812345678",con);
		assertEquals("Expected to 1 but actual " + Integer.toString(result),1, result);
		int resultZero = cardDAO.checkCard("0000000000000000",con);
		assertEquals("Expected to 0 but actual " + Integer.toString(result),0, resultZero);
	}

	public void testCheckExpire() {
		JPOS_CardDAO cardDAO = new JPOS_CardDAO();
		int result = cardDAO.checkExpire("1111111111111111",con);
		assertEquals("Expected to 1 but actual " + Integer.toString(result),1, result);
		int resultZero = cardDAO.checkExpire("1234567812345678",con);
		assertEquals("Expected to 0 but actual " + Integer.toString(result),0, resultZero);
	}
	
	public void testGetAmountCard(){
		JPOS_CardDAO cardDAO = new JPOS_CardDAO();
		int result = cardDAO.getAmountCard("8704353300000015", con);
		if(result == -1){
			fail();
		}else{
			System.out.println(result);
		}
	}
	
	public void testRedeem(){
		JPOS_CardDAO cardDAO = new JPOS_CardDAO();
		int result = cardDAO.redeem("8704353300000015",20000, con);
		if(result == -1){
			fail();
		}else{
			System.out.println(result);
		}
	}
	
	public void testReLoad(){
		JPOS_CardDAO cardDAO = new JPOS_CardDAO();
		int result = cardDAO.reloadCard("9704215000000046",20000, con);
		if(result == -1){
			fail();
		}else{
			System.out.println(result);
		}
	}
}
